package ru.job4j.sort;

/**
 * Class User.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 27.05.2017
 */
public class User implements Comparable<User> {
    /**
     * Поле имя.
     */
    private String name;
    /**
     * Поле возраст.
     */
    private Integer age;

    /**
     * Конструктор.
     *
     * @param name имя.
     * @param age  возраст.
     */
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Геттер age.
     *
     * @return возраст.
     */
    public Integer getAge() {
        return age;
    }

    /**
     * Геттер name.
     *
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * Переопределение метода.
     *
     * @param user объект User.
     * @return число.
     */
    @Override
    public int compareTo(User user) {
        return this.getAge().compareTo(user.getAge());
    }

    /**
     * Переопределение toString.
     *
     * @return строка.
     */
    @Override
    public String toString() {
        return "User{"
                + "name='"
                + name
                + '\''
                + ", age="
                + age
                + '}';
    }

    /**
     * Переопределение equals.
     * @param o объект.
     * @return результат.
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

        if (age != user.age) {
            return false;
        }
        return name != null ? name.equals(user.name) : user.name == null;
    }

    /**
     * Переопределение hashcode.
     * @return результат.
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }

    /**
     * Геттер для хэшкода.
     * @return хэш.
     */
    public Integer getHash() {
        return this.hashCode();
    }

    /**
     * Геттер для длины имени.
     * @return длина.
     */
    public Integer getLength() {
        return this.getName().length();
    }
}
