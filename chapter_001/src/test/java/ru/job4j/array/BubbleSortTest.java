package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* BubbleSort Test.
*
* @author Andrey Lemdyanov (mailto:lemdyanov5@mail.ru)
* @version $Id$
* @since 0.1
*/
public class BubbleSortTest {
/**
*Сортировка массива из 10 элементов.
*/
@Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
		BubbleSort bubble = new BubbleSort();
		int[] array = new int[] {7, 2, 4, 1, 55, 32, 6, 12, 63, 14};
		int[] resultArray = bubble.sort(array);
		int[] expectArray = new int[] {1, 2, 4, 6, 7, 12, 14, 32, 55, 63};
		assertThat(resultArray, is(expectArray));
	}
}