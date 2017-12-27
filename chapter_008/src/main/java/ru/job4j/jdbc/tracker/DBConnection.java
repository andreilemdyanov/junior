package ru.job4j.jdbc.tracker;

import java.sql.*;

/**
 * Class DBConnection.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 27.12.2017
 */
public class DBConnection {

    private Connection conn;

    /**
     * Геттер connection.
     *
     * @return connection.
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * Подключение к БД и создание таблицы
     */
    public void createTable() {
        String url = "jdbc:postgresql://localhost:5432/tracker";
        String username = "postgres";
        String password = "gh38Jo";
        try {
            conn = DriverManager.getConnection(url, username, password);
            Statement stat = conn.createStatement();
            String command = "CREATE TABLE IF NOT EXISTS item("
                    + " id SERIAL PRIMARY KEY,"
                    + " name VARCHAR(100),"
                    + " description VARCHAR(1000),"
                    + " create_date TIMESTAMP"
                    + ");";
            stat.executeUpdate(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод закрывает соединение.
     */
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
