package pro.ru.job4j.map;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Class UserNot.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 07.07.2017
 */
public class UserNot extends User {
    /**
     * Конструктор.
     *
     * @param name     имя.
     * @param children количество детей.
     * @param birthday день рождения.
     */
    public UserNot(String name, int children, GregorianCalendar birthday) {
        super(name, children, birthday);
    }

    /**
     * Точка входа.
     *
     * @param args массив строк.
     */
    public static void main(String[] args) {
        UserNot first = new UserNot("Ivan", 2, new GregorianCalendar(1990, 3, 25));
        UserNot second = new UserNot("Ivan", 2, new GregorianCalendar(1990, 3, 25));
        Map<User, Object> map = new HashMap<>();
        map.put(first, new Object());
        map.put(second, new Object());
        System.out.println(map);
    }
}
