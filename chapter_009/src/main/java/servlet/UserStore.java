package servlet;

import config.Settings;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Class UserStore.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 19.04.2018
 */
public enum UserStore {
    INSTANCE;
    private static final Logger LOG = LoggerFactory.getLogger(UserStore.class);

    UserStore() {
    }

    private Connection getConn() {
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream io = loader.getResourceAsStream("app.properties")) {
            settings.load(io);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }

        PoolProperties p = new PoolProperties();
        p.setUrl(settings.getValue("DB.url"));
        p.setDriverClassName(settings.getValue("DB.driver"));
        p.setUsername(settings.getValue("DB.username"));
        p.setPassword(settings.getValue("DB.password"));
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
                        + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer;"
                        + "org.apache.tomcat.jdbc.pool.interceptor.ResetAbandonedTimer");
        DataSource datasource = new DataSource();
        datasource.setPoolProperties(p);


        Connection conn = null;
        try {
            conn = datasource.getConnection();

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

        return conn;
    }

    public void createTable() {
        try
                (Connection conn = INSTANCE.getConn();
                 Statement stat = conn.createStatement()) {
            String command = "CREATE TABLE IF NOT EXISTS users("
                    + " id SERIAL PRIMARY KEY,"
                    + " name VARCHAR(100) NOT NULL ,"
                    + " login VARCHAR(1000)  NOT NULL ,"
                    + " email VARCHAR(1000)  NOT NULL ,"
                    + " createDate TIMESTAMP"
                    + ");";
            stat.executeUpdate(command);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection conn = INSTANCE.getConn();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM users")) {
            while (rs.next()) {
                String name = rs.getString("name");
                String login = rs.getString("login");
                String email = rs.getString("email");
                Calendar createDate = Calendar.getInstance();
                createDate.setTimeInMillis(rs.getTimestamp("createDate").getTime());
                User user = new User(name, login, email, createDate);
                list.add(user);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    public void createUser(String nameUser, String loginUser, String emailUser) {
        try (Connection conn = INSTANCE.getConn();
             PreparedStatement pst = conn.prepareStatement("INSERT INTO users(name, login, email, createDate) VALUES (?, ?, ?, ?);")) {
            pst.setString(1, nameUser);
            pst.setString(2, loginUser);
            pst.setString(3, emailUser);
            Calendar now = Calendar.getInstance();
            Timestamp t = new Timestamp(now.getTimeInMillis());
            pst.setTimestamp(4, t);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void updateUser(String loginForUp, String nameUser, String loginUser, String emailUser) {
        try (Connection conn = INSTANCE.getConn();
             PreparedStatement pst = conn.prepareStatement("UPDATE users SET name = ?,"
                     + "login = ?, email = ?, createDate = ? WHERE login = ?")) {
            pst.setString(1, nameUser);
            pst.setString(2, loginUser);
            pst.setString(3, emailUser);
            Timestamp t = new Timestamp(Calendar.getInstance().getTimeInMillis());
            pst.setTimestamp(4, t);
            pst.setString(5, loginForUp);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public void deleteUser(String loginUser) {
        try (Connection conn = INSTANCE.getConn();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM users WHERE login = ?")) {
            pst.setString(1, loginUser);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}

