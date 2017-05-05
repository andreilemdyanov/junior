package ru.job4j.task5;

import ru.job4j.task4.*;

/**
 * Class StartUI.
 *
 * @author Andrey Lemdyanov
 * @since 27.04.2017
 */
public class StartUI {
    /**
     * Поле класса Input.
     */
    private Input input;
    /**
     * Поле класса Tracker.
     */
    private Tracker tracker;

    /**
     * Конструктор.
     *
     * @param input   входящий параметр.
     * @param tracker входящий параметр.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод с операциями.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        do {
            menu.show();
            String key = input.ask("Select: ");
            menu.select(key);

        } while (MenuTracker.exit);

    }

    /**
     * Точка входа.
     *
     * @param args массив строк.
     */
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI(input, tracker).init();
    }

}
