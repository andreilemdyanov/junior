package ru.job4j.monitore;

/**
 * Class User.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 31.08.2017
 */
public class User {
    /**
     * Счет.
     */
    private int amount;
    /**
     * id.
     */
    private int id;

    /**
     * Геттер счета.
     *
     * @return счет.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Сеттер счета.
     *
     * @param amount счет.
     */
    public void setAmount(int amount) {
        synchronized (this) {
            this.amount = amount;
        }
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
     * Конструктор.
     *
     * @param id     id.
     * @param amount счет.
     */
    public User(int id, int amount) {
        this.amount = amount;
        this.id = id;
    }

    /**
     * Переопределение toString().
     *
     * @return строка.
     */
    @Override
    public String toString() {
        return "User{"
                + "amount="
                + amount
                + ", id="
                + id
                + '}';
    }

    /**
     * Переопределение equals().
     *
     * @param o объект для сравнения.
     * @return равен?
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

        if (amount != user.amount) {
            return false;
        }
        return id == user.id;
    }

    /**
     * Переопределение hashcode().
     *
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        int result = amount;
        result = 31 * result + id;
        return result;
    }
}
