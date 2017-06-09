package ru.job4j.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class SortUserTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 27.05.2017
 */
public class SortUserTest {
    /**
     * Тест сортировки по возрасту.
     */
    @Test
    public void sort() {
        ArrayList<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(new User("Ivan", 22),
                new User("Bob", 34),
                new User("Boris", 37),
                new User("Vova", 29),
                new User("Bill", 34),
                new User("Andrey", 34),
                new User("Jimm", 34),
                new User("Andy", 34)));
        SortUser example = new SortUser();
        Set<User> users = example.sort(list);
        Set<User> another = new TreeSet<>();
        another.addAll(Arrays.asList(new User("Ivan", 22),
                new User("Vova", 29),
                new User("Andrey", 34),
                new User("Andy", 34),
                new User("Bill", 34),
                new User("Bob", 34),
                new User("Jimm", 34),
                new User("Boris", 37)));
        assertThat(users, is(another));
    }

    /**
     * Тест сортировки по хэшкоду.
     */
    @Test
    public void sortHash() {
        SortUser ex = new SortUser();
        ArrayList<User> us = new ArrayList<>();
        us.addAll(Arrays.asList(new User("Ivan", 22),
                new User("Vova", 29),
                new User("Andrey", 34),
                new User("Andy", 34),
                new User("Bill", 34),
                new User("Bob", 34),
                new User("Jimm", 34),
                new User("Boris", 37)));
        List<User> result = ex.sortHash(us);
        List<User> another = new ArrayList<>();
        another.addAll(Arrays.asList(new User("Bob", 34),
                new User("Andy", 34),
                new User("Bill", 34),
                new User("Ivan", 22),
                new User("Jimm", 34),
                new User("Vova", 29),
                new User("Andrey", 34),
                new User("Boris", 37)));
        assertThat(result, is(another));
    }

    /**
     * Тест сортироки по длине имени.
     */
    @Test
    public void sortLength() {
        SortUser ex = new SortUser();
        ArrayList<User> us = new ArrayList<>();
        us.addAll(Arrays.asList(new User("Ivan", 22),
                new User("Vova", 29),
                new User("Andrey", 34),
                new User("Andy", 34),
                new User("Bill", 34),
                new User("Bob", 34),
                new User("Jimm", 34),
                new User("Boris", 37)));
        List<User> result = ex.sortLength(us);
        List<User> another = new ArrayList<>();
        another.addAll(Arrays.asList(new User("Bob", 34),
                new User("Andy", 34),
                new User("Bill", 34),
                new User("Ivan", 22),
                new User("Jimm", 34),
                new User("Vova", 29),
                new User("Boris", 37),
                new User("Andrey", 34)));
        assertThat(result, is(another));
    }

}