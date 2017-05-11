package ru.job4j.task5;


import ru.job4j.task4.Tracker;

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
     * Массив вариантов.
     */
    private int[] ranges = new int[]{0, 1, 2, 3, 4, 5, 6};

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
            menu.select(input.ask("Select: ", ranges));

        } while (menu.exit);

    }

    /**
     * Точка входа.
     *
     * @param args массив строк.
     */
    public static void main(String[] args) {
        Input input = new ValidateInput();
        Tracker tracker = new Tracker();
        new StartUI(input, tracker).init();
    }

}
