package ru.job4j.task5;

import ru.job4j.task4.Tracker;

/**
 * Class StartUITest.
 *
 * @author Andrey Lemdyanov
 * @since 27.04.2017
 */
public class StartUITest {
    /**
     * Точка входа.
     *
     * @param args массив строк.
     */
    public static void main(String[] args) {
        Input input = new StubInput(new String[]{"create stub task"});
        Tracker tracker = new Tracker();
        new StartUI(input, tracker).init();
    }
}