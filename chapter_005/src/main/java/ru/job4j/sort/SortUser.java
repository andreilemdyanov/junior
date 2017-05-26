package ru.job4j.sort;

import java.util.*;

/**
 * Class SortUser.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 27.05.2017
 */
public class SortUser {
    /**
     * Метод сортирует список в TreeSet.
     *
     * @param u список.
     * @return отсортированное множество.
     */
    public Set<User> sort(List<User> u) {
        Set<User> users = new TreeSet<>();
        for (User each : u) {
            users.add(each);
        }
        return users;
    }

}
