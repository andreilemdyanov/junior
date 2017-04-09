package ru.job4j.max;
/**
* Class Max.
*
* @author Andrey Lemdyanov (mailto:lemdyanov5@mail.ru)
* @version $Id$
* @since 0.1
*/
public class Max {
/**
*Метод возвращает большее число.
*@param first первый аргумент.
*@param second второй аргумент.
*@return большее из чисел.
*/
	public int max(int first, int second) {
		return first > second ? first : second;
	}
/**
*Метод возвращает большее из трех чисел.
*@param first первый аргумент.
*@param second второй аргумент.
*@param third третий аргумент.
*@return большее из чисел.
*/
	public int max(int first, int second, int third) {
		return this.max(this.max(first, second), third);
	}
}