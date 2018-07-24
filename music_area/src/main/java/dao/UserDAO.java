package dao;

import entities.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Class UserDAO.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
@Slf4j
public class UserDAO implements DAOImpl<User> {
    @Getter
    private UserStorage users;

    public UserDAO() {
        this.users = UserStorage.INSTANCE;
    }

    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        try (Connection conn = users.getPool().getConnection();
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery("SELECT * FROM users")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Calendar createDate = Calendar.getInstance();
                createDate.setTimeInMillis(rs.getTimestamp("createDate").getTime());
                int role = rs.getInt("role_id");

                User user = User.builder()
                        .id(id)
                        .login(login)
                        .password(password)
                        .firstName(firstName)
                        .lastName(lastName)
                        .timeCreate(createDate)
                        .roleId(role)
                        .build();
                list.add(user);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    public User get(int id) {
        User user = null;
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Calendar createDate = Calendar.getInstance();
                createDate.setTimeInMillis(rs.getTimestamp("createDate").getTime());
                int role = rs.getInt("role_id");

                user = User.builder()
                        .id(userId)
                        .login(login)
                        .password(password)
                        .firstName(firstName)
                        .lastName(lastName)
                        .timeCreate(createDate)
                        .roleId(role)
                        .build();
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return user;

    }

    public User get(String findLogin) {
        User user = null;
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM users WHERE login = ?")) {
            pst.setString(1, findLogin);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Calendar createDate = Calendar.getInstance();
                createDate.setTimeInMillis(rs.getTimestamp("createDate").getTime());
                int role = rs.getInt("role_id");

                user = User.builder()
                        .id(userId)
                        .login(login)
                        .password(password)
                        .firstName(firstName)
                        .lastName(lastName)
                        .timeCreate(createDate)
                        .roleId(role)
                        .build();
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return user;

    }

    public void create(User user) {
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("INSERT INTO users(login, password, firstName, lastName, createDate, role_id) VALUES (?, ?, ?, ?, ?, ?)")) {
            pst.setString(1, user.getLogin());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getFirstName());
            pst.setString(4, user.getLastName());
            pst.setTimestamp(5, new Timestamp(user.getTimeCreate().getTimeInMillis()));
            pst.setInt(6, user.getRoleId());
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void update(User user) {
        User user1 = this.get(user.getLogin());
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("UPDATE users SET login = ?, password = ?, firstName = ?, lastName = ?, createDate = ?, role_id = ? WHERE id = ?")) {
            pst.setString(1, user1.getLogin());
            pst.setString(2, user1.getPassword());
            pst.setString(3, user1.getFirstName());
            pst.setString(4, user1.getLastName());
            pst.setTimestamp(5, new Timestamp(user1.getTimeCreate().getTimeInMillis()));
            pst.setInt(6, user1.getRoleId());
            pst.setInt(7, user1.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void delete(int id) {
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }
}
