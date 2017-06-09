package ru.job4j.sort;


import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
        users.sort((o1, o2) ->
                o1.getHash().compareTo(o2.getHash()));
        return users;
    }

    /**
     * Сортировка по длине имени.
     *
     * @param users список.
     * @return отсортированный список.
     */
    public List<User> sortLength(List<User> users) {
        users.sort((o1, o2) -> {
            int result;
            if (o1.getLength().compareTo(o2.getLength()) == 0) {
                result = o1.getName().compareTo(o2.getName());
            } else {
                result = o1.getLength().compareTo(o2.getLength());
            }
            return result;
        });
        return users;
    }
}
