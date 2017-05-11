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
class EditItem implements UserAction {
    /**
     * Метод key.
     *
     * @return 2
     */
    public int key() {
        return 2;
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

    /**
     * Метод выводит информацию.
     *
     * @return строка.
     */
    public String info() {
        return String.format("%s. %s", this.key(), "Edit item");
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
        this.actions[0] = this.new AddItem();
        this.actions[1] = new MenuTracker.ShowItems();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new FindItemById();
        this.actions[5] = new FindItemByName();
        this.actions[6] = new Exit();
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
    private class AddItem implements UserAction {
        /**
         * Метод key.
         *
         * @return 0.
         */
        public int key() {
            return 0;
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

        /**
         * Метод выводит строку меню.
         *
         * @return строка.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Add new item");
        }
    }

    /**
     * Вложенный Class ShowItems.
     *
     * @author Andrey Lemdyanov
     * @since 04.05.2017
     */
    private static class ShowItems implements UserAction {
        /**
         * Метод key.
         *
         * @return 1.
         */
        public int key() {
            return 1;
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

        /**
         * Метод выводит строку меню.
         *
         * @return строка.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items");
        }
    }

    /**
     * Вложенный Class Exit.
     *
     * @author Andrey Lemdyanov
     * @since 04.05.2017
     */
    public class Exit implements UserAction {
        /**
         * Метод key.
         *
         * @return 6.
         */
        public int key() {
            return 6;
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

        /**
         * Метод выводит строку меню.
         *
         * @return строка.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Exit Program");
        }
    }

    /**
     * Вложенный Class DeleteItem.
     *
     * @author Andrey Lemdyanov
     * @since 04.05.2017
     */
    private class DeleteItem implements UserAction {
        /**
         * Метод key.
         *
         * @return 3.
         */
        public int key() {
            return 3;
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

        /**
         * Метод выводит строку меню.
         *
         * @return строка.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Delete item");
        }
    }

    /**
     * Вложенный Class FindItemById.
     *
     * @author Andrey Lemdyanov
     * @since 04.05.2017
     */
    private class FindItemById implements UserAction {
        /**
         * Метод key.
         *
         * @return 4.
         */
        public int key() {
            return 4;
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

        /**
         * Метод выводит строку меню.
         *
         * @return строка.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by id");
        }
    }

    /**
     * Вложенный Class FindItemByName.
     *
     * @author Andrey Lemdyanov
     * @since 04.05.2017
     */
    private class FindItemByName implements UserAction {
        /**
         * Метод key.
         *
         * @return 5.
         */
        public int key() {
            return 5;
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

        /**
         * Метод выводит строку меню.
         *
         * @return строка.
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Find items by name");
        }
    }
}
