package ru.job4j.task5;

/**
 * Class MenuOutException.
 *
 * @author Andrey Lemdyanov
 * @since 10.05.2017
 */
public class MenuOutException extends RuntimeException {
    /**
     * Конструктор класса.
     *
     * @param msg строка.
     */
    public MenuOutException(String msg) {
        super(msg);
    }
}
