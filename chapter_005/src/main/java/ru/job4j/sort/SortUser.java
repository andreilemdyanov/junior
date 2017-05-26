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

    /**
     * Сортировка по hashcode.
     *
     * @param users список.
     * @return отсортированный список.
     */
    public List<User> sortHash(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = 0;
                if (o1.hashCode() < o2.hashCode()) {
                    result = -1;
                } else if (o1.hashCode() > o2.hashCode()) {
                    result = 1;
                }
                return result;
            }
        });
        return users;
    }

    /**
     * Сортировка по длине имени.
     *
     * @param users список.
     * @return отсортированный список.
     */
    public List<User> sortLength(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int result = 0;
                if (o1.getName().length() == o2.getName().length()) {
                    result = o1.getName().compareTo(o2.getName());
                } else if (o1.getName().length() < o2.getName().length()) {
                    result = -1;
                } else if (o1.getName().length() > o2.getName().length()) {
                    result = 1;
                }
                return result;
            }
        });
        return users;
    }
}
