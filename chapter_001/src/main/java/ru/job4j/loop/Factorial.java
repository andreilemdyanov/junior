package ru.job4j.loop;
/**
*Class Factorial.
*
*@author Andrey Lemdyanov (mailto:lemdyanov5@mail.ru)
*@version $Id$
*@since 0.1
*/
public class Factorial {
/**
*Метод возвращает факториал от переданного числа.
*@param n входящее число
*@return count факториал
*/
	public int calc(int n) {
		if (n == 0) {
			return 1;
		}
		int start = 1;
		int count = 1;
		for (int i = start; i <= n; i++) {
			count *= i;
		}
		return count;
	}
}