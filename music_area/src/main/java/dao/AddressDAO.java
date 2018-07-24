package dao;

import entities.Address;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class AddressDAO.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
@Slf4j
public class AddressDAO implements DAOImpl<Address> {
    @Getter
    private UserStorage users;

    public AddressDAO() {
        this.users = UserStorage.INSTANCE;
    }

    public List<Address> getAll() {
        List<Address> list = new ArrayList<>();
        try (Connection conn = users.getPool().getConnection();
             Statement stat = conn.createStatement();
             ResultSet rs = stat.executeQuery("SELECT * FROM addresses")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String street = rs.getString("street");
                int userId = rs.getInt("user_id");

                Address type = Address.builder()
                        .id(id)
                        .country(country)
                        .city(city)
                        .street(street)
                        .userId(userId)
                        .build();
                list.add(type);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    public Address get(int id) {
        Address address = null;
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM addresses WHERE id = ?")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                address = Address.builder()
                        .id(rs.getInt("id"))
                        .country(rs.getString("country"))
                        .city(rs.getString("city"))
                        .street(rs.getString("street"))
                        .userId(rs.getInt("user_id"))
                        .build();
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return address;

    }

    public Address get(String country, String city, String street) {
        Address address = null;
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM addresses WHERE country = ? AND city = ? AND street = ?")) {
            pst.setString(1, country);
            pst.setString(2, city);
            pst.setString(3, street);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                address = Address.builder()
                        .id(rs.getInt("id"))
                        .country(rs.getString("country"))
                        .city(rs.getString("city"))
                        .street(rs.getString("street"))
                        .userId(rs.getInt("user_id"))
                        .build();
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return address;

    }

    public void create(Address address) {
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("INSERT INTO addresses(country, city, street, user_id) VALUES (?, ?, ?, ?)")) {
            pst.setString(1, address.getCountry());
            pst.setString(2, address.getCity());
            pst.setString(3, address.getStreet());
            pst.setInt(4, address.getUserId());
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void update(Address address) {
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("UPDATE addresses SET country = ?, city = ?, street = ?, user_id = ? WHERE id = ?")) {
            pst.setString(1, address.getCountry());
            pst.setString(2, address.getCity());
            pst.setString(3, address.getStreet());
            pst.setInt(4, address.getUserId());
            pst.setInt(5, address.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void delete(int userId) {
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM addresses WHERE user_id = ?")) {
            pst.setInt(1, userId);
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

}
