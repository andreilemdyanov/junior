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
    private int age;

    /**
     * Конструктор.
     *
     * @param name имя.
     * @param age  возраст.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Геттер age.
     *
     * @return возраст.
     */
    public int getAge() {
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
        int result = 0;
        if (this.getAge() == user.getAge()) {
            result = this.getName().compareTo(user.getName());
        } else if (this.getAge() < user.getAge()) {
            result = -1;
        } else if (this.getAge() > user.getAge()) {
            result = 1;
        }
        return result;
    }

    /**
     * Переопределение toString.
     *
     * @return строка.
     */
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
