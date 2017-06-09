package ru.job4j.task5;

import ru.job4j.task4.Item;
import ru.job4j.task4.Task;
import ru.job4j.task4.Tracker;

import java.util.ArrayList;

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
    EditItem() {
        super("Edit item", 2);
    }

    /**
     * Метод заменяет элемент с одинаковым id.
     *
     * @param input инпут.
     * @param tracker трэкер.
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
    private ArrayList<UserAction> actions = new ArrayList<>();
    /**
     * Поле для выхода.
     */
    private boolean exit = true;

    /**
     * Геттер exit.
     * @return exit.
     */
    public boolean isExit() {
        return exit;
    }
    /**
     * Конструктор.
     *
     * @param input инпут.
     * @param tracker трэкер.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод инициализирует массив.
     */
    public void fillActions() {
        this.actions.add(this.new AddItem());
        this.actions.add(new MenuTracker.ShowItems());
        this.actions.add(new EditItem());
        this.actions.add(new DeleteItem());
        this.actions.add(new FindItemById());
        this.actions.add(new FindItemByName());
        this.actions.add(new Exit());
    }

    /**
     * Метод для выбора действия.
     *
     * @param key ключ.
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод показывает меню.
     */
    public void show() {
        for (UserAction action : this.actions) {
                System.out.println(action.info());
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
        AddItem() {
            super("Add new item", 0);
        }

        /**
         * Метод добавляет заявку.
         *
         * @param input инпут.
         * @param tracker трэкер.
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
        ShowItems() {
            super("Show all items", 1);
        }

        /**
         * Метод выводит все заявки на экран.
         *
         * @param input инпут.
         * @param tracker трэкер.
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
        Exit() {
            super("Exit Program", 6);
        }

        /**
         * Метод выходит из программы.
         *
         * @param input инпут.
         * @param tracker трэкер.
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
        DeleteItem() {
            super("Delete item", 3);
        }

        /**
         * Метод удаляет заявку.
         *
         * @param input инпут.
         * @param tracker трэкер.
         */
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, enter id for delete: ");
            Task task = new Task("a", "b", System.currentTimeMillis());
            for (Item i : tracker.findAll()) {
                if (i.getId().equals(id)) {
                    task = (Task) i;
                }
            }
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
        FindItemById() {
            super("Find item by id", 4);
        }

        /**
         * Метод ищет заявку по id.
         *
         * @param input инпут.
         * @param tracker трэкер.
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
        FindItemByName() {
            super("Find items by name", 5);
        }

        /**
         * Метод выводит список заявок с определенным именем.
         *
         * @param input инпут.
         * @param tracker трэкер.
         */
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, enter name for search: ");
            ArrayList<Item> items = tracker.findByName(id);
            for (Item item : items) {
                System.out.println(item.getName() + " " + item.getDesc() + " " + item.getCreate());
            }
        }
    }
}
