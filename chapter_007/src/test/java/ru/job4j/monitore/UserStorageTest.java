package ru.job4j.monitore;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Class UserStorageTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 31.08.2017
 */
public class UserStorageTest {
    /**
     * Тест добавления.
     */
    @Test
    public void whenAddNewUserThenStorageHasIt() {
        UserStorage stoge = new UserStorage();
        stoge.add(new User(24, 100));
        assertThat(stoge.get(24), is(new User(24, 100)));
    }

    /**
     * Тест обновления.
     */
    @Test
    public void whenUpdateUserThenReplaceIt() {
        UserStorage stoge = new UserStorage();
        stoge.add(new User(24, 100));
        stoge.update(new User(24, 500));
        assertThat(stoge.get(24), is(new User(24, 500)));
    }

    /**
     * Тест удаления.
     */
    @Test
    public void whenDeleteUserThenStorageHasNotIt() {
        UserStorage stoge = new UserStorage();
        stoge.add(new User(24, 100));
        stoge.delete(24);
        assertNull(stoge.get(24));
    }

    /**
     * Тест перевода денег.
     */
    @Test
    public void whenTransferAmountThenUserGotIt() {
        UserStorage stoge = new UserStorage();
        stoge.add(new User(24, 100));
        stoge.add(new User(55, 200));
        stoge.transfer(24, 55, 50);
        assertThat(stoge.get(24), is(new User(24, 50)));
        assertThat(stoge.get(55), is(new User(55, 250)));
    }
}