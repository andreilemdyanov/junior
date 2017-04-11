package ru.job4j.array;
/**
*Class Turn.
*
*@author Andrey Lemdyanov (mailto:lemdyanov5@mail.ru)
*@version $Id$
*@since 0.1
*/
public class Turn {
/**
*Метод возвращает перевернутый массив.
*@param array принимает массив
*@return array возвращает перевернутый массив
*/
	public int[] back(int[] array) {
		for (int i = 0, j = array.length - 1; i != j & i < j; i++, j--) {
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
		return array;
	}
}