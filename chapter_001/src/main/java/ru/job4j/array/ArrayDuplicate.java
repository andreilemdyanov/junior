package ru.job4j.array;

import java.util.Arrays;
/**
*Class ArrayDuplicate.
*
*@author Andrey Lemdyanov (mailto:lemdyanov5@mail.ru)
*@version $Id$
*@since 0.1
*/
public class ArrayDuplicate {
/**
*Метод возвращает массив строк без дубликатов.
*@param array массив строк
*@return массив строк без дубликатов
*/
	public String[] remove(String[] array)	{
		 int count = 0;
       for (int i = 0; i < array.length; i++) {
           boolean flag = true;
            for (int j = i + 1; j < array.length; j++) {
                if ((array[i].equals(array[j])) & flag) {
                    String temp = array[array.length - 1 - count];
                    array[array.length - 1 - count] = array[j];
                    array[j] = temp;
                    count++;
                    flag = false;

                }
            }
       }
    return Arrays.copyOf(array, array.length - count);
	}
}