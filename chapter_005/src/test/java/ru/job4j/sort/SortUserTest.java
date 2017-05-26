package ru.job4j.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class SortUserTest
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 27.05.2017
 */
public class SortUserTest {

    @Test
    public void sort() throws Exception {
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

}