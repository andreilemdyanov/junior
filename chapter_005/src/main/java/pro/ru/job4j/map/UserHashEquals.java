package pro.ru.job4j.map;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Class UserHashEquals.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 07.07.2017
 */
public class UserHashEquals extends User {
    /**
     * Конструктор.
     *
     * @param name     имя.
     * @param children количество детей.
     * @param birthday ДР.
     */
    public UserHashEquals(String name, int children, GregorianCalendar birthday) {
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
     * Переопределение hashcode.
     *
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        int result = this.getName() != null ? this.getName().hashCode() : 0;
        result = 31 * result + this.getChildren();
        result = 31 * result + (this.getBirthday() != null ? this.getBirthday().hashCode() : 0);
        return result;
    }

    /**
     * Точка входа.
     *
     * @param args массив строк.
     */
    public static void main(String[] args) {
        UserHashEquals first = new UserHashEquals("Ivan", 2, new GregorianCalendar(1990, 3, 25));
        UserHashEquals second = new UserHashEquals("Ivan", 2, new GregorianCalendar(1990, 3, 25));
        Map<User, Object> map = new HashMap<>();
        map.put(first, new Object());
        map.put(second, new Object());
        System.out.println(map);
    }
}
