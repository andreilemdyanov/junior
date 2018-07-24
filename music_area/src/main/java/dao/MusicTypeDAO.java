package dao;

import entities.MusicType;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class MusicTypeDAO.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
@Slf4j
public class MusicTypeDAO implements DAOImpl<MusicType> {
    @Getter
    private UserStorage users;

    public MusicTypeDAO() {
        this.users = UserStorage.INSTANCE;
    }

    public List<MusicType> getAll() {
        List<MusicType> list = new ArrayList<>();
        try (Connection conn = users.getPool().getConnection();
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery("SELECT id, type FROM music_types")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("type");
                MusicType type = MusicType.builder()
                        .id(id)
                        .name(name)
                        .build();
                list.add(type);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    public MusicType get(int id) {
        MusicType type = null;
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT id, type FROM music_types WHERE id = ?")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                type = MusicType.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("type"))
                        .build();
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return type;

    }

    public MusicType get(String name) {
        MusicType type = null;
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM music_types WHERE type = ?")) {
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                type = MusicType.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("type"))
                        .build();
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return type;

    }

    public void create(MusicType type) {
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("INSERT INTO music_types(type) VALUES (?)")) {
            pst.setString(1, type.getName());
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void update(MusicType type) {
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("UPDATE music_types SET type = ? WHERE id = ?")) {
            pst.setString(1, type.getName());
            pst.setInt(2, type.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void delete(int id) {
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM music_types WHERE id = ?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

}
