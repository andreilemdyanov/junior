package ru.job4j.task4;
/**
*Class Task.
*
*@author Andrey Lemdyanov
*@since 27.04.2017
*/
public class Task extends Item {
	/**
	*Конструктор.
	*@param name имя.
	*@param desc описание.
	*@param create время создания.
	*/
	public Task(String name, String desc, long create) {
		super(name, desc, create);
	}
}