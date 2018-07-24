package dao;

import config.Settings;
import entities.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class UserStorage.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
@Slf4j
public enum UserStorage {

    INSTANCE;
    @Getter
    private DataSource pool;

    UserStorage() {
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

    public void createTables() {
        try (Connection conn = pool.getConnection();
             Statement stat = conn.createStatement()) {
            String command = "CREATE TABLE IF NOT EXISTS roles("
                    + " id SERIAL PRIMARY KEY,"
                    + " name VARCHAR(100) NOT NULL UNIQUE"
                    + ");";
            String command1 = "CREATE TABLE IF NOT EXISTS addresses("
                    + " id SERIAL PRIMARY KEY,"
                    + " country VARCHAR (100) NOT NULL,"
                    + " city VARCHAR (100) NOT NULL, "
                    + " street VARCHAR(100) NOT NULL, "
                    + " user_id INT UNIQUE NOT NULL, "
                    + " CONSTRAINT users_id_fk"
                    + " FOREIGN KEY (user_id)"
                    + " REFERENCES users (id) ON DELETE CASCADE"
                    + ")";
            String command2 = "CREATE TABLE IF NOT EXISTS music_types("
                    + " id SERIAL PRIMARY KEY,"
                    + " type VARCHAR (100) NOT NULL UNIQUE"
                    + ")";
            String command3 = "CREATE TABLE IF NOT EXISTS users("
                    + " id SERIAL PRIMARY KEY,"
                    + " login VARCHAR(1000)  NOT NULL UNIQUE,"
                    + " password VARCHAR(100) NOT NULL,"
                    + " firstName VARCHAR(100) NOT NULL,"
                    + " lastName VARCHAR(100) NOT NULL,"
                    + " createDate TIMESTAMP,"
                    + " role_id INT NOT NULL,"
                    + " CONSTRAINT roles_id_fk"
                    + " FOREIGN KEY (role_id) REFERENCES roles (id)"
                    + ");";
            String command4 = "CREATE TABLE IF NOT EXISTS user_music("
                    + " id SERIAL PRIMARY KEY,"
                    + " user_id INT NOT NULL,"
                    + " CONSTRAINT users_id_fk"
                    + " FOREIGN KEY (user_id)"
                    + " REFERENCES users (id),"
                    + " music_type_id INT NOT NULL,"
                    + " CONSTRAINT music_types_id_fk"
                    + " FOREIGN KEY (music_type_id)"
                    + " REFERENCES music_types (id)"
                    + ")";
            stat.executeUpdate(command);
            stat.executeUpdate(command2);
            stat.executeUpdate(command3);
            stat.executeUpdate(command1);
            stat.executeUpdate(command4);

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void fillRoles() {
        try (Connection conn = pool.getConnection();
             Statement st = conn.createStatement()) {
            st.executeUpdate("INSERT INTO roles(name) VALUES ('ADMIN'), ('USER'), ('MODERATOR')");
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void fillMusicTypes() {
        try (Connection conn = pool.getConnection();
             Statement st = conn.createStatement()) {
            st.executeUpdate("INSERT INTO music_types(type) VALUES ('RAP'), ('ROCK'), ('POP'), ('JAZZ')");
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public int isCredentional(String login, String password) {
        int exists = 0;
        for (User user : new UserDAO().getAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                if (new RoleDAO().get(user.getRoleId()).getName().equals("ADMIN")) {
                    exists = 1;
                } else if (new RoleDAO().get(user.getRoleId()).getName().equals("MODERATOR")) {
                    exists = 2;
                } else {
                    exists = 3;
                }
            }
        }
        return exists;
    }

    public void dropTables() {
        try (Connection conn = pool.getConnection();
             Statement stat = conn.createStatement()) {
            String command = "DROP TABLE users CASCADE ";
            String command1 = "DROP TABLE roles";
            String command2 = "DROP TABLE addresses";
            String command3 = "DROP TABLE music_types CASCADE ";
            String command4 = "DROP TABLE user_music";


            stat.executeUpdate(command);
            stat.executeUpdate(command1);
            stat.executeUpdate(command2);
            stat.executeUpdate(command3);
            stat.executeUpdate(command4);


        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

}

