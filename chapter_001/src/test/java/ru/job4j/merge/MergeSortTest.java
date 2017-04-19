package ru.job4j.merge;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* MergeSort Test.
*
* @author Andrey Lemdyanov (mailto:lemdyanov5@mail.ru)
* @version $Id$
* @since 0.1
*/
public class MergeSortTest {
	/**
	*Метод для проверки.
	*/
	@Test
	public void whenTwoArraysSortInOne() {
		MergeSort merge = new MergeSort();
		int[] a1 = new int[] {21, 23, 24, 40, 75, 76, 78};
		int[] a2 = new int[] {10, 11, 41, 50, 65, 86, 98, 101, 190};
		int[] resultArray = merge.sort(a1, a2);
		int[] expectArray = new int[] {10, 11, 21, 23, 24, 40, 41, 50, 65, 75, 76, 78, 86, 98, 101, 190};
		assertThat(resultArray, is(expectArray));
	}
}