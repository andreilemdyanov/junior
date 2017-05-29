package ru.job4j.bank;

/**
 * Class Account.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 29.05.2017
 */
public class Account {
    /**
     * Поле сумма.
     */
    private double value;
    /**
     * Поле реквизиты.
     */
    private String requisites;

    /**
     * Конструктор.
     *
     * @param value      сумма.
     * @param requisites реквизиты.
     */
    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * Геттер реквизитов.
     *
     * @return реквизиты.
     */
    public String getRequisites() {
        return requisites;
    }

    /**
     * Геттер суммы счета.
     *
     * @return сумма.
     */
    public double getValue() {
        return value;
    }

    /*
    Сеттер счета.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Переопределение equals.
     *
     * @param o объект.
     * @return результат.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (Double.compare(account.value, value) != 0) return false;
        return requisites != null ? requisites.equals(account.requisites) : account.requisites == null;
    }

    /**
     * Переопределение hashcode.
     *
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(value);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (requisites != null ? requisites.hashCode() : 0);
        return result;
    }
}
