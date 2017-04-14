package ru.job4j.array;
/**
*Class RotateArray.
*
*@author Andrey Lemdyanov (mailto:lemdyanov5@mail.ru)
*@version $Id$
*@since 0.1
*/
public class RotateArray {
/**
*Метод поворачивает квадратный массив по часовой стрелке на 90 градусов.
*@param array принимает двумерный массив
*@return array возвращает перевернутый массив
*/
	public int[][] rotate(int[][] array) {
		 for (int j = 0; j < array.length - 1; j++) {
            int i = 0;
            int temp = array[j][array.length - 1];
            array[j][array.length - 1] = array[i][j];
            int temp2 = array[array.length - 1][array.length - 1 - j];
            array[array.length - 1][array.length - 1 - j] = temp;
            temp = array[array.length - 1 - j][i];
            array[array.length - 1 - j][i] = temp2;
            array[i][j] = temp;

        }
		return array;
	}
}