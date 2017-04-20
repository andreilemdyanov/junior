package ru.job4j.task3;
/**
*Class Engineer.
*
*@author Andrey Lemdyanov
*@since 20.04.2017
*/
public class Engineer extends Profession {
	/**
	*Конструктор с 4 параметрами.
	*@param name имя
	*@param education образование
	*@param experience опыт
	*@param age возраст
	*/
	public Engineer(String name, String education, int experience, int age) {
		this.name = name;
		this.education = education;
		this.experience = experience;
		this.age = age;
	}
	/**
	*Метод рисовать чертеж.
	*@return строку о том что инженер рисует чертеж.
	*/
	public String draw() {
		return this.name + " рисует чертеж";
	}
	/**
	*Метод изобретать.
	*@return строку о том что инженер изобретает.
	*/
	public String invent() {
		return this.name + " изобретает что-то новое";
	}
	/**
	*Метод проектировать.
	*@return строку о том что инженер проектирует.
	*/
	public String design() {
		return this.name + " проектирует";
	}
}