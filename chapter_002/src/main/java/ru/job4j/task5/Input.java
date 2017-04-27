package ru.job4j.task5;
/**
*Interface ConsoleInput.
*
*@author Andrey Lemdyanov
*@since 27.04.2017
*/
public interface Input {
	/**
	*Метод спросить.
	*@param question вопрос.
	*@return ответ.
	*/
	String ask(String question);
}