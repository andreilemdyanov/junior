package pro.ru.job4j.map;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Class UserHash.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 07.07.2017
 */
public class UserHash extends User {
    /**
     * Конструктор.
     *
     * @param name     имя.
     * @param children количество детей.
     * @param birthday день рождения.
     */
    public UserHash(String name, int children, GregorianCalendar birthday) {
        super(name, children, birthday);
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
        UserHash first = new UserHash("Ivan", 2, new GregorianCalendar(1990, 3, 25));
        UserHash second = new UserHash("Ivan", 2, new GregorianCalendar(1990, 3, 25));
        Map<User, Object> map = new HashMap<>();
        map.put(first, new Object());
        map.put(second, new Object());
        System.out.println(map);
    }

}
