package ru.job4j.jdbc;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileOutputStream;
import java.sql.*;

/**
 * Class SQConnection.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 23.12.2017
 */
public class SQConnection {

    private SQLite sqlite;

    public Connection getCon() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(sqlite.getUrl());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public SQConnection(SQLite sqlite) {
        this.sqlite = sqlite;
    }

    /**
     * Основной метод, создается БД и вызываются методы для обработки данных.
     *
     * @throws SQLException исключения.
     */
    public void init() throws SQLException {
        try (Connection conn = this.getCon()) {
            if (conn != null) {
                this.insert();
                this.marshall();
                this.convertXml();
            }
        }
    }

    /**
     * Метод для создания таблицы и вставки значений в таблицу.
     *
     * @throws SQLException исключения.
     */
    private void insert() throws SQLException {
        String command = "CREATE TABLE IF NOT EXISTS TEST (FIELD SMALLINT)";
        String command1 = "DELETE FROM TEST";
        String command3 = "INSERT INTO TEST VALUES (?)";
        try (Connection conn = this.getCon()) {
            try (Statement stat = conn.createStatement()) {
                stat.executeUpdate(command);
                stat.executeUpdate(command1);
                PreparedStatement pstmt = conn.prepareStatement(command3);
                conn.setAutoCommit(false);
                for (int i = 1; i <= sqlite.getN(); i++) {
                    pstmt.setInt(1, i);
                    pstmt.addBatch();
                }
                pstmt.executeBatch();
                conn.commit();
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
                conn.rollback();
                conn.setAutoCommit(true);
            }
        }
    }

    /**
     * Метод для создания Xml файла, использую JAXB.
     */
    private void marshall() {
        String sql = "SELECT FIELD FROM TEST";
        try (Connection conn = this.getCon();
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery(sql)) {
            Entries entries = new Entries();
            while ((rs.next())) {
                Entry entry = new Entry();
                entry.setField(rs.getInt("FIELD"));
                entries.add(entry);
            }
            JAXBContext jContext = JAXBContext.newInstance(Entries.class);
            Marshaller marshallObj = jContext.createMarshaller();
            marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshallObj.marshal(entries,
                    new FileOutputStream("chapter_008/src/main/java/ru/job4j/jdbc/1.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void convertXml() {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer(
                    new StreamSource("chapter_008/src/main/java/ru/job4j/jdbc/style.xsl"));
            transformer.transform(
                    new StreamSource("chapter_008/src/main/java/ru/job4j/jdbc/1.xml"),
                    new StreamResult("chapter_008/src/main/java/ru/job4j/jdbc/2.xml"));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        long timestart = System.currentTimeMillis();
        SQLite sqLite = new SQLite();
        sqLite.setUrl("jdbc:sqlite:chapter_008/src/main/java/ru/job4j/jdbc/data.db");
        sqLite.setN(1000000);
        SQConnection sqConnection = new SQConnection(sqLite);
        try {
            sqConnection.init();
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
        SaxP saxP = new SaxP("chapter_008/src/main/java/ru/job4j/jdbc/2.xml");
        saxP.init();
        long endtime = System.currentTimeMillis() - timestart;
        System.out.printf("time = %s ms", endtime);
    }
}




