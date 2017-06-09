package ru.job4j.collection;

/**
 * //class User.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class User {
    /**
     * Поле name.
     */
    private String name;
    /**
     * Поле id.
     */
    private int id;
    /**
     * Поле city.
     */
    private String city;

    /**
     * Конструктор.
     *
     * @param id   ай ди.
     * @param name имя.
     * @param city город.
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Геттер id.
     *
     * @return id.
     */
    public int getId() {

        return id;
    }

    /**
     * Переопределение toString().
     *
     * @return строка.
     */
    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", id=" + id
                + ", city='"
                + city
                + '\''
                + '}';
    }
}
