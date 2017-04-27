package ru.job4j.task5;

import ru.job4j.task4.Tracker;
import ru.job4j.task4.Item;
import ru.job4j.task4.Task;
/**
*Class StartUI.
*
*@author Andrey Lemdyanov
*@since 27.04.2017
*/
public class StartUI {
	/**
	*Поле класса Input.
	*/
	private Input input;
	/**
	*Конструктор.
	*@param input входящий параметр.
	*/
	public StartUI(Input input) {
		this.input = input;
	}
	/**
	*Метод с операциями.
	*/
	public void init() {
		Tracker tracker = new Tracker();
		while (true) {
			System.out.println("0. Add new Item\n1. Show all items\n2. Edit item\n3. Delete item\n4. Find item by Id\n5. Find items by name\n6. Exit Program");
			String question = input.ask("Select: ");
			if (question.equals("")) {
				continue;
			} else if (Integer.valueOf(question) == 0) {
				String name = input.ask("Enter your name: ");
				String desc = input.ask("Enter description: ");
				tracker.add(new Task(name, desc, System.currentTimeMillis()));
			} else if (Integer.valueOf(question) == 1) {
				for (Item item : tracker.findAll()) {
					System.out.println(item.getName() + " " + item.getDesc() + " " + item.getCreate());
				}
			} else if (Integer.valueOf(question) == 2) {
				String id = input.ask("Enter id for replace item: ");
				String name = input.ask("Enter your name: ");
				String desc = input.ask("Enter description: ");
				Task task = new Task(name, desc, System.currentTimeMillis());
				task.setId(id);
				tracker.update(task);
			} else if (Integer.valueOf(question) == 3) {
				String del = input.ask("Enter id for delete item: ");
				for (Item item : tracker.findAll()) {
					if (item.getId().equals(del)) {
						tracker.delete(item);
					}
				}
			} else if (Integer.valueOf(question) == 4) {
				String find = input.ask("Enter id for find item: ");
				Item item = tracker.findById(find);
				System.out.print(item.getName() + " " + item.getDesc() + " " + item.getCreate());
			} else if (Integer.valueOf(question) == 5) {
				String name = input.ask("Enter name for find item: ");
				for (Item item : tracker.findByName(name)) {
					System.out.println(item.getName() + " " + item.getDesc() + " " + item.getCreate());
				}
			} else if (Integer.valueOf(question) == 6) {
				System.out.print("Bye bye");
				break;
			} else {
				System.out.print("Please, retry...\n\n");
			}
			}
	}
	/**
	*Точка входа.
	*@param args массив строк.
	*/
	public static void main(String[] args) {
		Input input = new ConsoleInput();
		new StartUI(input).init();
	}
}
