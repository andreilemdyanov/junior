package ru.job4j.task3;
/**
*Class Teacher.
*
*@author Andrey Lemdyanov
*@since 20.04.2017
*/
public class Teacher extends Profession {
	/**
	*Конструктор с 4 параметрами.
	*@param name имя
	*@param education образование
	*@param experience опыт
	*@param age возраст
	*/
	public Teacher(String name, String education, int experience, int age) {
		this.name = name;
		this.education = education;
		this.experience = experience;
		this.age = age;
	}
	/**
	*Метод проверить домашнюю работу.
	*@return оценку 5.
	*/
	public int checkHomework() {
		return 5;
	}
	/**
	*Метод учить.
	*@return строку о том что учитель учит учеников.
	*/
	public String teach() {
		return this.name + " учит учеников";
	}
	/**
	*Метод спросить.
	*@return строку о том что учитель задает вопрос.
	*/
	public String ask() {
		return this.name + " задает вопрос";
	}
}