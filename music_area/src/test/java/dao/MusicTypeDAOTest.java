package dao;

import entities.MusicType;
import org.junit.Test;
import repository.UserRepo;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class MusicTypeDAOTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
public class MusicTypeDAOTest {

    UserRepo repo;

    public MusicTypeDAOTest() {
        this.repo = new UserRepo();
        MusicTypeDAO daoM = repo.getMusicTypeDAO();
        daoM.getUsers().dropTables();
        daoM.getUsers().createTables();

    }

    @Test
    public void whenCreateNewMusicTypeThenDaoHasIt() {
        repo.getMusicTypeDAO().create(MusicType.builder()
                .id(1)
                .name("RAP")
                .build());
        MusicType rap = MusicType.builder()
                .id(1)
                .name("RAP")
                .build();

        assertThat(repo.getMusicTypeDAO().getAll().get(0), is(rap));
    }

    @Test
    public void whenUpdateMusicTypeThenDaoHasIt() {
        repo.getMusicTypeDAO().create(MusicType.builder()
                .id(1)
                .name("RAP")
                .build());
        repo.getMusicTypeDAO().update(MusicType.builder()
                .id(1)
                .name("ROCK")
                .build());
        MusicType rock = MusicType.builder()
                .id(1)
                .name("ROCK")
                .build();
        assertThat(repo.getMusicTypeDAO().getAll().get(0), is(rock));
    }

    @Test
    public void whenDeleteMusicTypeThenDaoHasNotIt() {
        repo.getMusicTypeDAO().create(MusicType.builder()
                .id(1)
                .name("RAP")
                .build());
        repo.getMusicTypeDAO().delete(1);

        assertThat(repo.getAddressDAO().getAll().size(), is(0));
    }

}