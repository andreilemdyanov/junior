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
public class ArrayDuplicateTest {
/**
*Метод для проверки удаления дубликатов.
*/
	@Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
		ArrayDuplicate array = new ArrayDuplicate();
		String[] str = new String[] {"Привет", "Мир", "Привет", "Супер", "Мир"};
		String[] resultArray = array.remove(str);
		String[] expectArray = new String[] {"Привет", "Мир", "Супер"};
		assertThat(resultArray, is(expectArray));
	}
}