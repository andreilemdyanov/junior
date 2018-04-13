package protocol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private String url;
    private String username;
    private String password;

    private UserStore() {
        url = "jdbc:postgresql://localhost:5432/userStore";
        username = "postgres";
        password = "gh38Jo";
    }

    public static UserStore getInstance() {
        return INSTANCE;
    }

    private Connection getConn() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }
        try {
            conn = DriverManager.getConnection(url, username, password);
            Statement stat = conn.createStatement();
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
        Connection conn = null;
        List<User> list = new ArrayList<>();
        try {
            conn = getConn();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users");
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
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return list;
    }

    public void createUser(String nameUser, String loginUser, String emailUser) {
        Connection conn = null;
        try {
            conn = getConn();
            PreparedStatement pst = conn.prepareStatement("INSERT INTO users(name, login, email, createDate) VALUES (?, ?, ?, ?);");
            pst.setString(1, nameUser);
            pst.setString(2, loginUser);
            pst.setString(3, emailUser);
            Calendar now = Calendar.getInstance();
            Timestamp t = new Timestamp(now.getTimeInMillis());
            pst.setTimestamp(4, t);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    public void updateUser(String loginForUp, String nameUser, String loginUser, String emailUser) {
        Connection conn = null;
        try {
            conn = getConn();
            PreparedStatement pst = conn.prepareStatement("UPDATE users SET name = ?,"
                    + "login = ?, email = ?, createDate = ? WHERE login = ?");
            pst.setString(1, nameUser);
            pst.setString(2, loginUser);
            pst.setString(3, emailUser);
            Timestamp t = new Timestamp(Calendar.getInstance().getTimeInMillis());
            pst.setTimestamp(4, t);
            pst.setString(5, loginForUp);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    public void deleteUser(String loginUser) {
        Connection conn = null;

        try {
            conn = getConn();
            PreparedStatement pst = conn.prepareStatement("DELETE FROM users WHERE login = ?");
            pst.setString(1, loginUser);
            pst.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    public static void main(String[] args) {
        UserStore userStore = new UserStore();
        userStore.createUser("a", "d", "f");
        System.out.println(userStore.getAllUsers());
    }
}

