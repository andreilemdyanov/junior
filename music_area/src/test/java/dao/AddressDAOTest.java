package dao;

import entities.Address;
import entities.User;
import org.junit.Test;
import repository.UserRepo;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class AddressDAOTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
public class AddressDAOTest {

    UserRepo repo;

    public AddressDAOTest() {
        this.repo = new UserRepo();
        AddressDAO daoA = repo.getAddressDAO();
        daoA.getUsers().dropTables();
        daoA.getUsers().createTables();
        daoA.getUsers().fillRoles();

    }

    @Test
    public void whenCreateNewAddressThenDaoHasIt() {
        repo.getUserDAO().create(User.builder()
                .id(1)
                .login("login")
                .password("pass")
                .firstName("and")
                .lastName("sec")
                .timeCreate(Calendar.getInstance())
                .roleId(1)
                .build());
        Address address = Address.builder()
                .id(1)
                .country("Russia")
                .city("Moscow")
                .street("Jukova")
                .userId(1)
                .build();
        repo.getAddressDAO().create(Address.builder()
                .id(1)
                .country("Russia")
                .city("Moscow")
                .street("Jukova")
                .userId(1)
                .build());

        assertThat(repo.getAddressDAO().getAll().get(0), is(address));
    }

    @Test
    public void whenUpdateAddressThenDaoHasIt() {
        repo.getUserDAO().create(User.builder()
                .id(1)
                .login("login")
                .password("pass")
                .firstName("and")
                .lastName("sec")
                .timeCreate(Calendar.getInstance())
                .roleId(1)
                .build());
        Address address = Address.builder()
                .id(1)
                .country("some")
                .city("some")
                .street("some")
                .userId(1)
                .build();
        repo.getAddressDAO().create(Address.builder()
                .id(1)
                .country("Russia")
                .city("Moscow")
                .street("Jukova")
                .userId(1)
                .build());
        repo.getAddressDAO().update(Address.builder()
                .id(1)
                .country("some")
                .city("some")
                .street("some")
                .userId(1)
                .build());

        assertThat(repo.getAddressDAO().getAll().get(0), is(address));
    }

    @Test
    public void whenDeleteAddressThenDaoHasNotIt() {
        repo.getUserDAO().create(User.builder()
                .id(1)
                .login("login")
                .password("pass")
                .firstName("and")
                .lastName("sec")
                .timeCreate(Calendar.getInstance())
                .roleId(1)
                .build());
        repo.getAddressDAO().create(Address.builder()
                .id(1)
                .country("Russia")
                .city("Moscow")
                .street("Jukova")
                .userId(1)
                .build());
        repo.getAddressDAO().delete(1);

        assertThat(repo.getAddressDAO().getAll().size(), is(0));
    }

}