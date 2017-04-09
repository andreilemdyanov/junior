package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Test.
*
* @author Andrey Lemdyanov (mailto:lemdyanov5@mail.ru)
* @version $Id$
* @since 0.1
*/
public class MaxTest {
/**
*Когда первое число больше.
*/
@Test
	public void whenFirstMoreSecondThenFirst() {
		Max number = new Max();
		int maximum = number.max(3, 2);
		int expected = 3;
		assertThat(maximum, is(expected));
	}
/**
*Когда второе число больше.
*/
@Test
	public void whenFirstLessSecondThenSecond() {
		Max number = new Max();
		int maximum = number.max(2, 4);
		int expected = 4;
		assertThat(maximum, is(expected));
	}
/**
*Сравнение трех чисел когда первое больше.
*/
@Test
	public void whenFirstMoreSecondAndThirdThenFirst() {
		Max number = new Max();
		int maximum = number.max(7, 3, 5);
		int expected = 7;
		assertThat(maximum, is(expected));
	}
}