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
    private Connection conn;

    public SQConnection(SQLite sqlite) {
        this.sqlite = sqlite;
    }

    /**
     * Основной метод, создается БД и вызываются методы для обработки данных.
     *
     * @throws SQLException исключения.
     */
    public void init() throws SQLException {
        try {
            conn = DriverManager.getConnection(sqlite.getUrl());
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();

                System.out.printf("The driver name is %s\n", meta.getDriverName());
                System.out.println("A new database has been created.");

                this.insert();
                this.marshall();
                this.convertXml();
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Метод для создания таблицы и вставки значений в таблицу.
     *
     * @throws SQLException исключения.
     */
    private void insert() throws SQLException {
        try (Statement stat = conn.createStatement()) {
            System.out.println("Start table");
            String command = "CREATE TABLE IF NOT EXISTS TEST(\n" + " FIELD SMALLINT\n" + ");";
            stat.execute(command);
            String command1 = "DELETE FROM TEST;";
            stat.execute(command1);
            String command3 = "INSERT INTO TEST VALUES (?)";
            PreparedStatement pstmt = conn.prepareStatement(command3);
            conn.setAutoCommit(false);
            for (int i = 1; i <= sqlite.getN(); i++) {
                pstmt.setInt(1, i);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            conn.commit();
        }
        System.out.println("Execute table");
    }

    /**
     * Метод для создания Xml файла, использую JAXB.
     *
     * @throws SQLException исключения.
     */
    private void marshall() throws SQLException {
        System.out.println("Start create 1.xml");
        String sql = "SELECT FIELD FROM TEST";
        try (Statement stat = conn.createStatement();
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
                    new FileOutputStream("C:/projects/junior/chapter_008/src/main/java/ru/job4j/jdbc/1.xml"));
            System.out.println("Finish create 1.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void convertXml() {
        try {
            System.out.println("XSLT start");
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer(
                    new StreamSource("C:/projects/junior/chapter_008/src/main/java/ru/job4j/jdbc/style.xsl"));
            transformer.transform(
                    new StreamSource("C:/projects/junior/chapter_008/src/main/java/ru/job4j/jdbc/1.xml"),
                    new StreamResult("C:/projects/junior/chapter_008/src/main/java/ru/job4j/jdbc/2.xml"));
            System.out.println("XSLT complete");
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        long timestart = System.currentTimeMillis();
        SQLite sqLite = new SQLite();
        sqLite.setUrl("jdbc:sqlite:C:/projects/junior/chapter_008/src/main/java/ru/job4j/jdbc/data.db");
        sqLite.setN(1000000);
        SQConnection sqConnection = new SQConnection(sqLite);
        try {
            sqConnection.init();
        } catch (SQLException e) {
            for (Throwable t : e) {
                t.printStackTrace();
            }
        }
        System.out.println("Start SaxP");
        SaxP saxP = new SaxP("C:/projects/junior/chapter_008/src/main/java/ru/job4j/jdbc/2.xml");
        saxP.init();
        System.out.println("Finish SaxP");
        long endtime = System.currentTimeMillis() - timestart;
        System.out.printf("time = %s ms", endtime);
    }
}




