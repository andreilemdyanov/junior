package dao;

import entities.Role;
import org.junit.Test;
import repository.UserRepo;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class RoleDAOTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
public class RoleDAOTest {

    UserRepo repo;

    public RoleDAOTest() {
        this.repo = new UserRepo();
        RoleDAO daoM = repo.getRoleDAO();
        daoM.getUsers().dropTables();
        daoM.getUsers().createTables();

    }

    @Test
    public void whenCreateNewRoleThenDaoHasIt() {
        repo.getRoleDAO().create(Role.builder()
                .id(1)
                .name("ADMIN")
                .build());
        Role admin = Role.builder()
                .id(1)
                .name("ADMIN")
                .build();

        assertThat(repo.getRoleDAO().getAll().get(0), is(admin));
    }

    @Test
    public void whenUpdateRoleThenDaoHasIt() {
        repo.getRoleDAO().create(Role.builder()
                .id(1)
                .name("ADMIN")
                .build());
        repo.getRoleDAO().update(Role.builder()
                .id(1)
                .name("USER")
                .build());
        Role user = Role.builder()
                .id(1)
                .name("USER")
                .build();
        assertThat(repo.getRoleDAO().getAll().get(0), is(user));
    }

    @Test
    public void whenDeleteRoleThenDaoHasNotIt() {
        repo.getRoleDAO().create(Role.builder()
                .id(1)
                .name("ADMIN")
                .build());
        repo.getRoleDAO().delete(1);

        assertThat(repo.getRoleDAO().getAll().size(), is(0));
    }

}