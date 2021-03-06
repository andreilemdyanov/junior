package jsp;

import config.Settings;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

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
 * @since 16.05.2018
 */
@Slf4j
public enum UserStore {

    INSTANCE;
    private DataSource pool;

    UserStore() {
        pool = createPool();
    }

    private DataSource createPool() {
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream io = loader.getResourceAsStream("app.properties")) {
            settings.load(io);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
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

        return datasource;
    }

    public void createTable() {
        try (Connection conn = pool.getConnection();
             Statement stat = conn.createStatement()) {
            String command1 = "CREATE TABLE IF NOT EXISTS roles("
                    + " id SERIAL PRIMARY KEY,"
                    + " name VARCHAR(100) NOT NULL UNIQUE"
                    + ");";
            String command = "CREATE TABLE IF NOT EXISTS users("
                    + " id SERIAL PRIMARY KEY,"
                    + " name VARCHAR(100) NOT NULL,"
                    + " login VARCHAR(1000)  NOT NULL UNIQUE,"
                    + " password VARCHAR(100) NOT NULL,"
                    + " email VARCHAR(1000)  NOT NULL,"
                    + " createDate TIMESTAMP,"
                    + " role_id INT NOT NULL,"
                    + " CONSTRAINT roles_id_fk"
                    + " FOREIGN KEY (role_id) REFERENCES roles (id),"
                    + " country VARCHAR(100) NOT NULL,"
                    + " city VARCHAR(100) NOT NULL "
                    + ");";
            stat.executeUpdate(command1);
            stat.executeUpdate(command);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection conn = pool.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT u.id, u.name, u.login, u.password, u.email, u.createDate, u.role_id, u.country, u.city, r.name AS role_name FROM users AS u JOIN roles AS r ON u.role_id = r.id")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Calendar createDate = Calendar.getInstance();
                createDate.setTimeInMillis(rs.getTimestamp("createdate").getTime());
                Role role = new Role(rs.getInt("role_id"), rs.getString("role_name"));
                String country = rs.getString("country");
                String city = rs.getString("city");
                User user = new User.UserBuilder()
                        .id(id)
                        .name(name)
                        .login(login)
                        .password(password)
                        .email(email)
                        .createDate(createDate)
                        .role(role)
                        .country(country)
                        .city(city)
                        .build();
                list.add(user);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    public User getUser(String name, String login) {
        User user = null;
        try (Connection conn = pool.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT u.id, u.name, u.login, u.password, u.email, u.createDate, u.role_id, u.country, u.city, r.name AS role_name FROM users AS u JOIN roles AS r ON u.role_id = r.id WHERE u.name = ? AND u.login = ? ")) {
            pst.setString(1, name);
            pst.setString(2, login);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name1 = rs.getString("name");
                String login1 = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                Calendar createDate = Calendar.getInstance();
                createDate.setTimeInMillis(rs.getTimestamp("createdate").getTime());
                Role role = new Role(rs.getInt("role_id"), rs.getString("role_name"));
                String country = rs.getString("country");
                String city = rs.getString("city");
                user = new User.UserBuilder()
                        .id(id)
                        .name(name1)
                        .login(login1)
                        .password(password)
                        .email(email)
                        .createDate(createDate)
                        .role(role)
                        .country(country)
                        .city(city)
                        .build();
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return user;

    }

    public void createRoles() {
        try (Connection conn = pool.getConnection();
             Statement st = conn.createStatement()) {
            st.executeUpdate("INSERT INTO roles(name) VALUES ('ADMIN'), ('DEFAULT')");
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void createUser(String nameUser, String loginUser, String password, String emailUser, int role, String country, String city) {
        try (Connection conn = pool.getConnection();
             PreparedStatement pst = conn.prepareStatement("INSERT INTO users(name, login, password, email, createDate, role_id, country, city) VALUES (?, ?, ?, ?, ?, ?, ?, ?);")) {
            pst.setString(1, nameUser);
            pst.setString(2, loginUser);
            pst.setString(3, password);
            pst.setString(4, emailUser);
            Calendar now = Calendar.getInstance();
            Timestamp t = new Timestamp(now.getTimeInMillis());
            pst.setTimestamp(5, t);
            pst.setInt(6, role);
            pst.setString(7, country);
            pst.setString(8, city);
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void updateRole(int id, int roleNumber) {
        try (Connection conn = pool.getConnection();
             PreparedStatement pst = conn.prepareStatement("UPDATE users SET role_id = ? WHERE id = ?;");) {
            pst.setInt(1, roleNumber);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void updateUser(int id, String nameUser, String loginUser, String password, String emailUser, String country, String city) {
        try (Connection conn = pool.getConnection();
             PreparedStatement pst = conn.prepareStatement("UPDATE users SET name = ?,"
                     + "login = ?, password = ?, email = ?, createDate = ?, country = ?, city = ? WHERE id = ?")) {
            pst.setString(1, nameUser);
            pst.setString(2, loginUser);
            pst.setString(3, password);
            pst.setString(4, emailUser);
            Timestamp t = new Timestamp(Calendar.getInstance().getTimeInMillis());
            pst.setTimestamp(5, t);
            pst.setString(6, country);
            pst.setString(7, city);
            pst.setInt(8, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void deleteUser(int id) {
        try (Connection conn = pool.getConnection();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public int isCredentional(String login, String password) {
        int exists = 0;
        for (User user : this.getAllUsers()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                if (user.getRole().getName().equals("ADMIN")) {
                    exists = 1;
                } else {
                    exists = 2;
                }
            }
        }
        return exists;
    }

    public void dropTables() {
        try (Connection conn = pool.getConnection();
             Statement stat = conn.createStatement()) {
            String command1 = "DROP TABLE users";
            String command = "DROP TABLE roles";
            stat.executeUpdate(command1);
            stat.executeUpdate(command);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

}

