package pro.ru.job4j.map;

import java.util.GregorianCalendar;

/**
 * Class User.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 08.07.2017
 */
public class User {
    /**
     * Имя.
     */
    private String name;
    /**
     * Количество детей.
     */
    private int children;
    /**
     * День рождения.
     */
    private GregorianCalendar birthday;

    /**
     * Конструктор.
     *
     * @param name     имя.
     * @param children количество детей.
     * @param birthday день рождения.
     */
    public User(String name, int children, GregorianCalendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * Геттер имени.
     *
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * Геттер количества детей.
     *
     * @return количество детей.
     */
    public int getChildren() {
        return children;
    }

    /**
     * Геттер ДР.
     *
     * @return ДР.
     */
    public GregorianCalendar getBirthday() {
        return birthday;
    }

}
