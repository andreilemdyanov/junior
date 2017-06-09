package ru.job4j.bank;

/**
 * Class User.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 29.05.2017
 */
public class User {
    /**
     * Поле имя.
     */
    private String name;
    /**
     * Поле паспорт.
     */
    private String passport;

    /**
     * Конструктор.
     *
     * @param name     имя.
     * @param passport паспорт.
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Переопределение equals.
     *
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

        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        return passport != null ? passport.equals(user.passport) : user.passport == null;
    }

    /**
     * Переопределение hashcode.
     *
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        return result;
    }
}
