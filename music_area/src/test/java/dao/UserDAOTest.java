package dao;

import entities.User;
import org.junit.Test;
import repository.UserRepo;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class UserDAOTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
public class UserDAOTest {

    UserRepo repo;

    public UserDAOTest() {
        this.repo = new UserRepo();
        UserDAO daoM = repo.getUserDAO();
        daoM.getUsers().dropTables();
        daoM.getUsers().createTables();
        daoM.getUsers().fillRoles();


    }

    @Test
    public void whenCreateNewUserThenDaoHasIt() {
        repo.getUserDAO().create(User.builder()
                .login("login")
                .password("pass")
                .firstName("and")
                .lastName("sec")
                .timeCreate(Calendar.getInstance())
                .roleId(1)
                .build());
        User user = User.builder()
                .id(1)
                .login("login")
                .password("pass")
                .firstName("and")
                .lastName("sec")
                .timeCreate(repo.getUserDAO().get(1).getTimeCreate())
                .roleId(1)
                .build();

        assertThat(repo.getUserDAO().getAll().get(0), is(user));
    }

    @Test
    public void whenUpdateUserThenDaoHasIt() {
        repo.getUserDAO().create(User.builder()
                .login("login")
                .password("pass")
                .firstName("and")
                .lastName("sec")
                .timeCreate(Calendar.getInstance())
                .roleId(1)
                .build());
        User user = User.builder()
                .id(1)
                .login("login")
                .password("pass")
                .firstName("and")
                .lastName("sec")
                .timeCreate(repo.getUserDAO().get(1).getTimeCreate())
                .roleId(1)
                .build();
        repo.getUserDAO().update(user);
        assertThat(repo.getUserDAO().getAll().get(0), is(user));
    }

    @Test
    public void whenDeleteUserThenDaoHasNotIt() {
        repo.getUserDAO().create(User.builder()
                .login("login")
                .password("pass")
                .firstName("and")
                .lastName("sec")
                .timeCreate(Calendar.getInstance())
                .roleId(1)
                .build());
        repo.getUserDAO().delete(1);

        assertThat(repo.getUserDAO().getAll().size(), is(0));
    }

}