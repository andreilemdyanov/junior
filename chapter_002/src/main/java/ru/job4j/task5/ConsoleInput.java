package ru.job4j.task5;

import java.util.Scanner;
/**
*Class ConsoleInput.
*
*@author Andrey Lemdyanov
*@since 27.04.2017
*/
public class ConsoleInput implements Input {
	/**
	*Объект для ввода.
	*/
	private Scanner scanner = new Scanner(System.in);
	/**
	*Метод вопрос пользователю.
	*@param question вопрос
	*@return строка ввода
	*/
	public String ask(String question) {
		System.out.print(question);
		return scanner.nextLine();
	}
}