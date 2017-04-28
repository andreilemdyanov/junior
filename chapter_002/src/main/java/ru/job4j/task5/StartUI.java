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
	*Поле класса Tracker.
	*/
	private Tracker tracker;
	/**
	*Конструктор.
	*@param input входящий параметр.
	*/
	public StartUI(Input input) {
		this.input = input;
		tracker = new Tracker();
	}
	/**
	*Метод с операциями.
	*/
	public void init() {
		boolean flag = true;
		while (flag) {
			System.out.println(String.format("0. Add new Item%s1. Show all items%<s2. Edit item%<s3. Delete item%<s4. Find item by Id%<s5. Find items by name%<s6. Exit Program", System.getProperty("line.separator")));
			String question = input.ask("Select: ");
			if (question.equals("")) {
				continue;
			}
			int ques = Integer.valueOf(question);
			if (ques == Select.ADD.ordinal()) {
				String name = input.ask("Enter your name: ");
				String desc = input.ask("Enter description: ");
				tracker.add(new Task(name, desc, System.currentTimeMillis()));
			} else if (ques == Select.SHOW.ordinal()) {
				for (Item item : tracker.findAll()) {
					System.out.println(item.getName() + " " + item.getDesc() + " " + item.getCreate());
				}
			} else if (ques == Select.EDIT.ordinal()) {
				String id = input.ask("Enter id for replace item: ");
				String name = input.ask("Enter your name: ");
				String desc = input.ask("Enter description: ");
				Task task = new Task(name, desc, System.currentTimeMillis());
				task.setId(id);
				tracker.update(task);
			} else if (ques == Select.DEL.ordinal()) {
				String del = input.ask("Enter id for delete item: ");
				for (Item item : tracker.findAll()) {
					if (item.getId().equals(del)) {
						tracker.delete(item);
					}
				}
			} else if (ques == Select.FINDID.ordinal()) {
				String find = input.ask("Enter id for find item: ");
				Item item = tracker.findById(find);
				System.out.print(item.getName() + " " + item.getDesc() + " " + item.getCreate());
			} else if (ques == Select.FINDNAME.ordinal()) {
				String name = input.ask("Enter name for find item: ");
				for (Item item : tracker.findByName(name)) {
					System.out.println(item.getName() + " " + item.getDesc() + " " + item.getCreate());
				}
			} else if (ques == Select.EXIT.ordinal()) {
				System.out.print("Bye bye");
				flag = false;
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
	/**
	*Перечисление.
	*/
	enum Select {
		/**
		*Добавить заявку.
		*/
		ADD(),
		/**
		*Показать все заявки.
		*/
		SHOW(),
		/**
		*Редактировать заявку.
		*/
		EDIT(),
		/**
		*Удалить заявку.
		*/
		DEL(),
		/**
		*Найти заявку по id.
		*/
		FINDID(),
		/**
		*Найти заявку по имени.
		*/
		FINDNAME(),
		/**
		*Выйти из программы.
		*/
		EXIT();
	}
}
