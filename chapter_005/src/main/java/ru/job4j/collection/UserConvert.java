package ru.job4j.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * //class UserConvert.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class UserConvert {
    /**
     * Метод перезаписывает список в мэп.
     *
     * @param list список.
     * @return мэп.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> map = new HashMap<>();
        for (User user : list) {
            map.put(user.getId(), user);
        }
        return map;
    }

    /**
     * Точка входа.
     *
     * @param args массив строк.
     */
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(
                new User(4, "Ivan", "Moscow"),
                new User(56, "Bob", "London"),
                new User(2, "Boris", "Ekaterinburg"),
                new User(6, "Vova", "Omsk")));

        UserConvert convert = new UserConvert();
        HashMap<Integer, User> result = convert.process(list);
        System.out.println(result);

    }
}
