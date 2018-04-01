package ru.job4j.additional.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Calendar;

/**
 * Class DBWork.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 01.04.2018
 */
public class DBWork {

    private static final Logger LOG = LoggerFactory.getLogger(DBWork.class);

    private int count = 0;

    public Connection getConnection() {
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();
        try {
            try (InputStream io = loader.getResourceAsStream("app.properties")) {
                settings.load(io);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        String url = settings.getValue("DB.url");
        String name = settings.getValue("DB.username");
        String password = settings.getValue("DB.password");

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,
                    name, password);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return conn;
    }

    public void createNewTables() {
        try {
            try (Connection conn = getConnection();
                 Statement stat = conn.createStatement()) {

                String command1 = "CREATE TABLE IF NOT EXISTS authors ("
                        + "id SERIAL PRIMARY KEY,"
                        + "name VARCHAR(1000) UNIQUE"
                        + ");";

                String command2 = "CREATE TABLE IF NOT EXISTS vacancies ("
                        + "id SERIAL PRIMARY KEY,"
                        + "name VARCHAR(1000) NOT NULL UNIQUE,"
                        + "url VARCHAR(1000) NOT NULL,"
                        + "description TEXT NOT NULL,"
                        + "timeCreate TIMESTAMP,"
                        + "author_id INT NOT NULL,"
                        + "CONSTRAINT authors_id_fk"
                        + " FOREIGN KEY (author_id) REFERENCES authors (id));";

                String command3 = "CREATE TABLE IF NOT EXISTS launch_timepoints ("
                        + " id SERIAL PRIMARY KEY,"
                        + " time TIMESTAMP"
                        + ");";

                stat.executeUpdate(command1);
                stat.executeUpdate(command2);
                stat.executeUpdate(command3);

                while (count == 0) {
                    try (PreparedStatement psmt = conn.prepareStatement("INSERT INTO launch_timepoints(time) VALUES (?)")) {
                        Timestamp t = new Timestamp(00000);
                        psmt.setTimestamp(1, t);
                        psmt.executeUpdate();
                        count++;
                    }
                }
            }

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

    }

    public void insertNewArticle(Article art) {
        try {
            try (Connection conn = getConnection();
                 PreparedStatement psmt = conn.prepareStatement("INSERT INTO authors(name) VALUES (?)")) {
                psmt.setString(1, art.getAuthor());
                psmt.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void insertNewVacanci(Article art) {
        try {
            try (Connection conn = getConnection();
                 PreparedStatement psmt2 = conn.prepareStatement("INSERT INTO vacancies(name, url, description, timeCreate, author_id) "
                         + "VALUES (?,?,?,?,?)");
                 PreparedStatement stat = conn.prepareStatement("SELECT id FROM authors WHERE authors.name = ?")) {
                stat.setString(1, art.getAuthor());
                ResultSet rs = stat.executeQuery();
                Integer i = -1;
                while (rs.next()) {
                    i = rs.getInt(1);
                }
                psmt2.setString(1, art.getName());
                psmt2.setString(2, art.getUrl());
                psmt2.setString(3, art.getDesc());
                Timestamp t = new Timestamp(art.getTime().getTimeInMillis());
                psmt2.setTimestamp(4, t);
                psmt2.setInt(5, i);
                psmt2.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public Calendar getLastLaunch() {
        Calendar cal = Calendar.getInstance();
        try {
            try (Connection conn = getConnection();
                 Statement stat = conn.createStatement()) {
                ResultSet rs = stat.executeQuery("SELECT time FROM launch_timepoints");
                while (rs.next()) {
                    Timestamp stamp = rs.getTimestamp(1);
                    cal.setTimeInMillis(stamp.getTime());
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return cal;
    }

    public void setLastLaunch() {
        Calendar cal = Calendar.getInstance();
        Timestamp stamp = new Timestamp(cal.getTimeInMillis());
        try {
            try (Connection conn = getConnection();
                 Statement statement = conn.createStatement();
                 PreparedStatement stat = conn.prepareStatement("INSERT INTO launch_timepoints(time) VALUES (?)")) {
                statement.executeUpdate("DELETE FROM launch_timepoints");
                stat.setTimestamp(1, stamp);
                stat.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
