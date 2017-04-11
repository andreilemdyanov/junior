package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Turn Test.
*
* @author Andrey Lemdyanov (mailto:lemdyanov5@mail.ru)
* @version $Id$
* @since 0.1
*/
public class TurnTest {
/**
*Метод для проверки массива с четным числом элементов.
*/
@Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
		Turn array = new Turn();
		int[] arr = new int[] {3, 4, 6, 7, 9, 15};
		int[] resultArray = array.back(arr);
		int[] expectArray = new int[] {15, 9, 7, 6, 4, 3};
		assertThat(resultArray, is(expectArray));
	}
/**
*Метод для проверки массива с нечетным числом элементов.
*/
@Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
		Turn array = new Turn();
		int[] arr = new int[] {6, 4, 3, 7, 9, 15, 8};
		int[] resultArray = array.back(arr);
		int[] expectArray = new int[] {8, 15, 9, 7, 3, 4, 6};
		assertThat(resultArray, is(expectArray));
	}
}