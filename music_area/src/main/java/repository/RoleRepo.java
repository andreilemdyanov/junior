package repository;

import dao.UserStorage;
import entities.Role;
import entities.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Class RoleRepo.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
@Slf4j
public class RoleRepo {
    @Getter
    private UserStorage users;

    public RoleRepo() {
        this.users = UserStorage.INSTANCE;
    }

    public Map<Role, List> getRoleWithEntities(Role role) {
        Map<Role, List> map = new HashMap<>();
        List<User> list = new ArrayList();
        Role role1 = null;
        try (Connection conn = users.getPool().getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT u.id, u.login, u.password, u.firstName, u.lastName, u.createDate, u.role_id, r.name AS role_name FROM users AS u JOIN roles AS r ON u.role_id = r.id AND u.role_id = ?")) {
            pst.setInt(1, role.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Calendar createDate = Calendar.getInstance();
                createDate.setTimeInMillis(rs.getTimestamp("createDate").getTime());
                int roleId = rs.getInt("role_id");
                String roleName = rs.getString("role_name");
                role1 = Role.builder()
                        .id(roleId)
                        .name(roleName)
                        .build();
                User user1 = User.builder()
                        .id(id)
                        .login(login)
                        .password(password)
                        .firstName(firstName)
                        .lastName(lastName)
                        .timeCreate(createDate)
                        .roleId(roleId)
                        .build();

                list.add(user1);
            }
            map.put(role1, list);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return map;
    }

}
