package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* RotateArray Test.
*
* @author Andrey Lemdyanov (mailto:lemdyanov5@mail.ru)
* @version $Id$
* @since 0.1
*/

public class RotateArrayTest {
/**
*Метод для проверки массива 2*2.
*/
    @Test
    public void whenRotateTwoRowTwoColArrayThenRotatedArray() {
	RotateArray mas = new RotateArray();
	int[][] array = new int[][] {{1, 2}, {3, 4}};
	int[][] resultArray = mas.rotate(array);
	int[][] expectArray = new int[][] {{3, 1}, {4, 2}};
	assertThat(resultArray, is(expectArray));
	}
/**
*Метод ля проверки массива 3*3.
*/
	@Test
    public void whenRotateThreeRowThreeColArrayThenRotatedArray() {
	RotateArray mas = new RotateArray();
	int[][] array = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
	int[][] resultArray = mas.rotate(array);
	int[][] expectArray = new int[][] {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
	assertThat(resultArray, is(expectArray));
	}
}