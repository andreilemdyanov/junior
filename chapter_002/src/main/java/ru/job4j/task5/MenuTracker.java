package ru.job4j.task5;

import ru.job4j.task4.Item;
import ru.job4j.task4.Task;
import ru.job4j.task4.Tracker;

/**
 * Class EditItem.
 *
 * @author Andrey Lemdyanov
 * @since 04.05.2017
 */
class EditItem extends BaseAction {
    /**
     * Конструктор.
     */
    public EditItem() {
        super("Edit item", 2);
    }

    /**
     * Метод заменяет элемент с одинаковым id.
     *
     * @param input
     * @param tracker
     */
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Please, enter the task's id: ");
        String name = input.ask("Enter your name: ");
        String desc = input.ask("Enter description: ");
        Task task = new Task(name, desc, System.currentTimeMillis());
        task.setId(id);
        tracker.update(task);
    }

}

/**
 * Class MenuTracker.
 *
 * @author Andrey Lemdyanov
 * @since 04.05.2017
 */
public class MenuTracker {
    /**
     * Поле input.
     */
    private Input input;
    /**
     * Поле Tracker.
     */
    private Tracker tracker;
    /**
     * Массив вариантов выбора.
     */
    private UserAction[] actions = new UserAction[7];
    /**
     * Поле для выхода.
     */
    public boolean exit = true;
    /**
     * Позиция в массиве.
     */
    public int position = 0;

    /**
     * Конструктор.
     *
     * @param input
     * @param tracker
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод инициализирует массив.
     */
    public void fillActions() {
        this.actions[position++] = this.new AddItem();
        this.actions[position++] = new MenuTracker.ShowItems();
        this.actions[position++] = new EditItem();
        this.actions[position++] = new DeleteItem();
        this.actions[position++] = new FindItemById();
        this.actions[position++] = new FindItemByName();
        this.actions[position++] = new Exit();
    }

    /**
     * Метод для выбора действия.
     *
     * @param key
     */
    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * Метод показывает меню.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Вложенный Class AddItem.
     *
     * @author Andrey Lemdyanov
     * @since 04.05.2017
     */
    private class AddItem extends BaseAction {
        /**
         * Конструктор.
         */
        public AddItem() {
            super("Add new item", 0);
        }

        /**
         * Метод добавляет заявку.
         *
         * @param input
         * @param tracker
         */
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Enter your name: ");
            String desc = input.ask("Enter description: ");
            tracker.add(new Task(name, desc, System.currentTimeMillis()));
        }

    }

    /**
     * Вложенный Class ShowItems.
     *
     * @author Andrey Lemdyanov
     * @since 04.05.2017
     */
    private static class ShowItems extends BaseAction {
        /**
         * Конструктор.
         */
        public ShowItems() {
            super("Show all items", 1);
        }

        /**
         * Метод выводит все заявки на экран.
         *
         * @param input
         * @param tracker
         */
        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.findAll()) {
                System.out.println(String.format("%s. %s", item.getId(), item.getName()));
            }
        }

    }

    /**
     * Вложенный Class Exit.
     *
     * @author Andrey Lemdyanov
     * @since 04.05.2017
     */
    public class Exit extends BaseAction {
        /**
         * Конструктор.
         */
        public Exit() {
            super("Exit Program", 6);
        }

        /**
         * Метод выходит из программы.
         *
         * @param input
         * @param tracker
         */
        public void execute(Input input, Tracker tracker) {
            exit = false;
        }

    }

    /**
     * Вложенный Class DeleteItem.
     *
     * @author Andrey Lemdyanov
     * @since 04.05.2017
     */
    private class DeleteItem extends BaseAction {
        /**
         * Конструктор.
         */
        public DeleteItem() {
            super("Delete item", 3);
        }

        /**
         * Метод удаляет заявку.
         *
         * @param input
         * @param tracker
         */
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, enter id for delete: ");
            Task task = new Task("a", "b", System.currentTimeMillis());
            task.setId(id);
            tracker.delete(task);

        }

    }

    /**
     * Вложенный Class FindItemById.
     *
     * @author Andrey Lemdyanov
     * @since 04.05.2017
     */
    private class FindItemById extends BaseAction {
        /**
         *
         */
        public FindItemById() {
            super("Find item by id", 4);
        }

        /**
         * Метод ищет заявку по id.
         *
         * @param input
         * @param tracker
         */
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, enter id for search: ");
            Item item = tracker.findById(id);
            System.out.println(item.getName() + " " + item.getDesc() + " " + item.getCreate());
        }

    }

    /**
     * Вложенный Class FindItemByName.
     *
     * @author Andrey Lemdyanov
     * @since 04.05.2017
     */
    private class FindItemByName extends BaseAction {
        /**
         *
         */
        public FindItemByName() {
            super("Find items by name", 5);
        }

        /**
         * Метод выводит список заявок с определенным именем.
         *
         * @param input
         * @param tracker
         */
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, enter name for search: ");
            Item[] items = tracker.findByName(id);
            for (Item item : items) {
                System.out.println(item.getName() + " " + item.getDesc() + " " + item.getCreate());
            }
        }
    }
}
