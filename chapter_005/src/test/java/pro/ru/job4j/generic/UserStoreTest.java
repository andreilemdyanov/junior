package pro.ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class UserStoreTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 22.06.2017
 */
public class UserStoreTest {
    /**
     * Тест добавления.
     */
    @Test
    public void whenAddFiveUsersThenItWorks() {
        UserStore us = new UserStore(new SimpleArray<>(5));
        us.addUser(new User("1"));
        us.addUser(new User("2"));
        us.addUser(new User("3"));
        us.addUser(new User("4"));
        us.addUser(new User("5"));
        SimpleArray result = us.getUser();
        SimpleArray<User> expected = new SimpleArray<>(5);
        expected.add(new User("1"));
        expected.add(new User("2"));
        expected.add(new User("3"));
        expected.add(new User("4"));
        expected.add(new User("5"));
        assertThat(result, is(expected));
    }

    /**
     * Тест обновления.
     */
    @Test
    public void whenUpdateSecondUserThenReplacedIt() {
        UserStore us = new UserStore(new SimpleArray<>(5));
        us.addUser(new User("1"));
        User b = new User("2");
        us.addUser(b);
        us.addUser(new User("3"));
        us.addUser(new User("4"));
        us.addUser(new User("5"));
        us.updateUser(2, new User("6"));
        SimpleArray result = us.getUser();
        SimpleArray<User> expected = new SimpleArray<>(5);
        expected.add(new User("1"));
        expected.add(new User("2"));
        expected.add(new User("6"));
        expected.add(new User("4"));
        expected.add(new User("5"));
        assertThat(result, is(expected));
    }

    /**
     * Тест удаления.
     */
    @Test
    public void whenDeleteFirstUserThenItsGone() {
        UserStore us = new UserStore(new SimpleArray<>(5));
        us.addUser(new User("1"));
        User b = new User("2");
        us.addUser(b);
        us.addUser(new User("3"));
        us.addUser(new User("4"));
        us.addUser(new User("5"));
        us.deleteUser(b);
        SimpleArray result = us.getUser();
        SimpleArray<User> expected = new SimpleArray<>(4);
        expected.add(new User("1"));
        expected.add(new User("3"));
        expected.add(new User("4"));
        expected.add(new User("5"));
        assertThat(result, is(expected));
    }
}