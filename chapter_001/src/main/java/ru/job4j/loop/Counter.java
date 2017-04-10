package ru.job4j.loop;
/**
* Class Counter.
*
* @author Andrey Lemdyanov (mailto:lemdyanov5@mail.ru)
* @version $Id$
* @since 0.1
*/
public class Counter {
/**
*Метод возвращает сумму четных чисел от start до finish.
*@param start начальное число
*@param finish конечное число
*@return count сумма четных чисел
*/
	public int add(int start, int finish) {
		int count = 0;
		for (int i = start; i <= finish; i++) {
			if (i % 2 == 0) {
				count = count + i;
			}
		}
		return count;
	}
}