package repository;

import dao.*;
import entities.Address;
import entities.MusicType;
import entities.Role;
import entities.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.*;

/**
 * Class UserRepo.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
@Slf4j
public class UserRepo {
    @Getter
    private UserStorage users;
    @Getter
    private UserDAO userDAO;
    @Getter
    private RoleDAO roleDAO;
    @Getter
    private AddressDAO addressDAO;
    @Getter
    private MusicTypeDAO musicTypeDAO;

    public UserRepo() {
        this.users = UserStorage.INSTANCE;
        userDAO = new UserDAO();
        roleDAO = new RoleDAO();
        addressDAO = new AddressDAO();
        musicTypeDAO = new MusicTypeDAO();
    }

    public void createUserWithEntities(User user, Role role, Address address, List<MusicType> musicType) {
        List<MusicType> list = new ArrayList<>();
        roleDAO.create(role);
        Role role1 = roleDAO.get(role.getName());
        User userForIns = User.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .timeCreate(user.getTimeCreate())
                .roleId(role1.getId())
                .build();
        userDAO.create(userForIns);
        User user1 = userDAO.get(user.getLogin());
        Address address1 = Address.builder()
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .userId(user1.getId())
                .build();
        addressDAO.create(address1);
        for (MusicType m : musicType) {
            musicTypeDAO.create(m);
            list.add(musicTypeDAO.get(m.getName()));
        }

        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("INSERT INTO user_music(user_id, music_type_id) VALUES (?, ?);")) {
            pst.setInt(1, user1.getId());
            for (MusicType m : list) {
                pst.setInt(2, m.getId());
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void updateUserWithEntities(User user, Role role, Address address, List<MusicType> musicType) {
        Role role1 = roleDAO.get(role.getName());
        User userForUp = User.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .timeCreate(user.getTimeCreate())
                .roleId(role1.getId())
                .build();
        userDAO.update(userForUp);
        addressDAO.delete(user.getId());
        Address address1 = Address.builder()
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .userId(user.getId())
                .build();
        addressDAO.create(address1);

        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM user_music WHERE user_id = ?")) {
            pst.setInt(1, user.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);

        }
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("INSERT INTO user_music(user_id, music_type_id) VALUES (?, ?);")) {
            pst.setInt(1, user.getId());
            for (MusicType m : musicType) {
                pst.setInt(2, m.getId());
                pst.executeUpdate();
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void deleteUserWithEntities(int id) {
        addressDAO.delete(id);
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("DELETE FROM user_music WHERE user_id = ?")) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        userDAO.delete(id);
    }


    public Map<User, List> getAllUsersWithEntities() {
        Map<User, List> allUsers = new HashMap<>();
        try (Connection conn = users.getPool().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT u.id, u.login, u.password, u.firstName, u.lastName, u.createDate, u.role_id, a.id AS address_id, r.name AS role_name, a.country, a.city, a.street FROM users AS u JOIN roles AS r ON u.role_id = r.id JOIN addresses AS a ON u.id = a.user_id")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Calendar createDate = Calendar.getInstance();
                createDate.setTimeInMillis(rs.getTimestamp("createDate").getTime());
                int role = rs.getInt("role_id");
                int address = rs.getInt("address_id");

                Role role1 = Role.builder()
                        .id(role)
                        .name(rs.getString("role_name"))
                        .build();

                Address address1 = Address.builder()
                        .id(address)
                        .country(rs.getString("country"))
                        .city(rs.getString("city"))
                        .street(rs.getString("street"))
                        .userId(id)
                        .build();

                User user = User.builder()
                        .id(id)
                        .login(login)
                        .password(password)
                        .firstName(firstName)
                        .lastName(lastName)
                        .timeCreate(createDate)
                        .roleId(role)
                        .build();
                List<MusicType> list = this.getAllMusicTypesForUser(user);

                allUsers.put(user, new ArrayList(Arrays.asList(role1, address1, list)));
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return allUsers;
    }

    public List<MusicType> getAllMusicTypesForUser(User user) {
        List<MusicType> list = new ArrayList<>();
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT m.id, m.type  FROM music_types AS m JOIN user_music AS um ON m.id = um.music_type_id WHERE um.user_id = ?")) {
            pst.setInt(1, user.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(MusicType.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("type"))
                        .build());
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    public List<User> findUserOnAddress(Address address) {
        List<User> list = new ArrayList<>();
        Address address1 = addressDAO.get(address.getCountry(), address.getCity(), address.getStreet());
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT u.id, u.login, u.password, u.firstName, u.lastName, u.createDate FROM users AS u JOIN addresses AS a ON u.id = a.user_id WHERE a.user_id = ?")) {
            if (address1 == null) {
                pst.setInt(1, address.getId());
            } else {
                pst.setInt(1, address1.getId());
            }
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Calendar createDate = Calendar.getInstance();
                createDate.setTimeInMillis(rs.getTimestamp("createDate").getTime());
                User user = User.builder()
                        .id(rs.getInt("id"))
                        .login(rs.getString("login"))
                        .password(rs.getString("password"))
                        .firstName(rs.getString("firstName"))
                        .lastName(rs.getString("lastName"))
                        .timeCreate(createDate)
                        .build();
                list.add(user);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    public List<User> findUserOnRole(Role role) {
        List<User> list = new ArrayList<>();
        Role role1 = roleDAO.get(role.getName());
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT u.id, u.login, u.password, u.firstName, u.lastName, u.createDate, u.role_id FROM users AS u WHERE u.role_id = ?")) {
            if (role1 == null) {
                pst.setInt(1, role.getId());
            } else {
                pst.setInt(1, role1.getId());

            }
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Calendar createDate = Calendar.getInstance();
                createDate.setTimeInMillis(rs.getTimestamp("createDate").getTime());
                User user = User.builder()
                        .id(rs.getInt("id"))
                        .login(rs.getString("login"))
                        .password(rs.getString("password"))
                        .firstName(rs.getString("firstName"))
                        .lastName(rs.getString("lastName"))
                        .timeCreate(createDate)
                        .roleId(rs.getInt("role_id"))
                        .build();
                list.add(user);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }

    public List<User> findUserOnMusicType(MusicType musicType) {
        List<User> list = new ArrayList<>();
        MusicType musicType1 = musicTypeDAO.get(musicType.getName());
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT u.id, u.login, u.password, u.firstName, u.lastName, u.createDate, u.role_id FROM users AS u JOIN user_music AS um ON u.id = um.user_id AND um.music_type_id = ?")) {
            if (musicType1 == null) {
                pst.setInt(1, musicType.getId());
            } else {
                pst.setInt(1, musicType1.getId());
            }
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Calendar createDate = Calendar.getInstance();
                createDate.setTimeInMillis(rs.getTimestamp("createDate").getTime());
                User user = User.builder()
                        .id(rs.getInt("id"))
                        .login(rs.getString("login"))
                        .password(rs.getString("password"))
                        .firstName(rs.getString("firstName"))
                        .lastName(rs.getString("lastName"))
                        .timeCreate(createDate)
                        .roleId(rs.getInt("role_id"))
                        .build();
                list.add(user);
            }

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return list;
    }
}
