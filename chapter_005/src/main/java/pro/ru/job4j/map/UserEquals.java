package pro.ru.job4j.map;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Class UserEquals.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 07.07.2017
 */
public class UserEquals extends User {
    /**
     * Конструктор.
     *
     * @param name     имя.
     * @param children количество детей.
     * @param birthday ДР.
     */
    public UserEquals(String name, int children, GregorianCalendar birthday) {
        super(name, children, birthday);
    }

    /**
     * Переопределение equals.
     *
     * @param o объект.
     * @return да/нет.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (this.getChildren() != user.getChildren()) {
            return false;
        }
        if (this.getName() != null ? !this.getName().equals(user.getName()) : user.getName() != null) {
            return false;
        }
        return this.getBirthday() != null ? this.getBirthday().equals(user.getBirthday()) : user.getBirthday() == null;
    }

    /**
     * Вызов hashcode родителя.
     *
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Точка входа.
     *
     * @param args массив строк.
     */
    public static void main(String[] args) {
        UserEquals first = new UserEquals("Ivan", 2, new GregorianCalendar(1990, 3, 25));
        UserEquals second = new UserEquals("Ivan", 2, new GregorianCalendar(1990, 3, 25));
        Map<User, Object> map = new HashMap<>();
        map.put(first, new Object());
        map.put(second, new Object());
        System.out.println(map);
    }
}
