package protocol;

import config.Settings;
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
 * @since 13.04.2018
 */
public class UserStore {
    private static final Logger LOG = LoggerFactory.getLogger(UserStore.class);

    private static final UserStore INSTANCE = new UserStore();

    private UserStore() {
    }

    public static UserStore getInstance() {
        return INSTANCE;
    }

    private Connection getConn() {
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream io = loader.getResourceAsStream("app.properties")) {
            settings.load(io);
            Class.forName("DB.driver");
        } catch (IOException | ClassNotFoundException e) {
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
        try
                (Statement stat = conn.createStatement()) {
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
        return conn;
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection conn = getConn();
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
        try (Connection conn = getConn();
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
        try (Connection conn = getConn();
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
        try (Connection conn = getConn();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM users WHERE login = ?")) {
            pst.setString(1, loginUser);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}

