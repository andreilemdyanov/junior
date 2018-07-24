package dao;

import entities.Role;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class RoleDAO.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
@Slf4j
public class RoleDAO implements DAOImpl<Role> {
    @Getter
    private UserStorage users;

    public RoleDAO() {
        this.users = UserStorage.INSTANCE;
    }

    public List<Role> getAll() {
        List<Role> list = new ArrayList<>();
        try (Connection conn = users.getPool().getConnection();
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery("SELECT id, name FROM roles")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Role role = Role.builder()
                        .id(id)
                        .name(name)
                        .build();
                list.add(role);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    public Role get(int id) {
        Role role = null;
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT id, name FROM roles WHERE id = ?")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                role = Role.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build();
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return role;

    }

    public Role get(String name) {
        Role role = null;
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM roles WHERE name = ?")) {
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                role = Role.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build();
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return role;

    }

    public void create(Role role) {
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("INSERT INTO roles(name) VALUES (?)")) {
            pst.setString(1, role.getName().toUpperCase());
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void update(Role role) {
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("UPDATE roles SET name = ? WHERE id = ?")) {
            pst.setString(1, role.getName().toUpperCase());
            pst.setInt(2, role.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void delete(int id) {
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM roles WHERE id = ?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

}
