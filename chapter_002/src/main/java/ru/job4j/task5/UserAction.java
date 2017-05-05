package ru.job4j.task5;

import ru.job4j.task4.Tracker;

/**
 * Interface UserAction.
 *
 * @author Andrey Lemdyanov
 * @since 04.05.2017
 */
public interface UserAction {
    int key();

    void execute(Input input, Tracker tracker);

    String info();
}
