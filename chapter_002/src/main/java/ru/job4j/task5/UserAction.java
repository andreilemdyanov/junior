package ru.job4j.task5;

import ru.job4j.task4.Tracker;

/**
 * Interface UserAction.
 *
 * @author Andrey Lemdyanov
 * @since 04.05.2017
 */
public interface UserAction {
    /**
     * Выполнить.
     * @param input инпут.
     * @param tracker трэкер.
     */
    void execute(Input input, Tracker tracker);

    /**
     * Метод инфо.
     * @return инфо.
     */
    String info();
}
