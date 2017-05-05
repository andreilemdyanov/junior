package ru.job4j.task5;

import ru.job4j.task4.*;

/**
 * Class MenuTracker.
 *
 * @author Andrey Lemdyanov
 * @since 04.05.2017
 */
class EditItem implements UserAction {
    public int key() {
        return 2;
    }

    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Please, enter the task's id: ");
        String name = input.ask("Enter your name: ");
        String desc = input.ask("Enter description: ");
        Task task = new Task(name, desc, System.currentTimeMillis());
        task.setId(id);
        tracker.update(task);
    }

    public String info() {
        return String.format("%s. %s", this.key(), "Edit item");
    }
}

public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[7];
    public static boolean exit = true;

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillActions() {
        this.actions[0] = this.new AddItem();
        this.actions[1] = new MenuTracker.ShowItems();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new FindItemById();
        this.actions[5] = new FindItemByName();
        this.actions[6] = new MenuTracker.Exit();
    }

    public void select(String key) {
        int keynew = Integer.valueOf(key);
        this.actions[keynew].execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    private class AddItem implements UserAction {
        public int key() {
            return 0;
        }

        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Enter your name: ");
            String desc = input.ask("Enter description: ");
            tracker.add(new Task(name, desc, System.currentTimeMillis()));
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Add new item");
        }
    }

    private static class ShowItems implements UserAction {
        public int key() {
            return 1;
        }

        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.findAll()) {
                System.out.println(String.format("%s. %s", item.getId(), item.getName()));
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Show all items");
        }
    }

    private static class Exit implements UserAction {
        public int key() {
            return 6;
        }

        public void execute(Input input, Tracker tracker) {
            MenuTracker.exit = false;
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Exit Program");
        }
    }

    private class DeleteItem implements UserAction {
        public int key() {
            return 3;
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, enter id for delete: ");
            Task task = new Task("a", "b", System.currentTimeMillis());
            task.setId(id);
            tracker.delete(task);

        }

        public String info() {
            return String.format("%s. %s", this.key(), "Delete item");
        }
    }

    private class FindItemById implements UserAction {
        public int key() {
            return 4;
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, enter id for search: ");
            Item item = tracker.findById(id);
            System.out.println(item.getName() + " " + item.getDesc() + " " + item.getCreate());
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Find item by id");
        }
    }

    private class FindItemByName implements UserAction {
        public int key() {
            return 5;
        }

        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, enter name for search: ");
            Item[] items = tracker.findByName(id);
            for (Item item : items) {
                System.out.println(item.getName() + " " + item.getDesc() + " " + item.getCreate());
            }
        }

        public String info() {
            return String.format("%s. %s", this.key(), "Find items by name");
        }
    }
}
