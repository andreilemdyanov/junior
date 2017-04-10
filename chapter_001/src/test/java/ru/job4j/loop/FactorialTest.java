package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Factorial Test.
*
* @author Andrey Lemdyanov (mailto:lemdyanov5@mail.ru)
* @version $Id$
* @since 0.1
*/
public class FactorialTest {
/**
*Test factorial 5.
*/
@Test
	public void whenCalculateFactorialForFiveThenOneHundreedTwenty() {
		Factorial fact = new Factorial();
		int result = fact.calc(5);
		int expected = 120;
		assertThat(result, is(expected));
	}
/**
*Test factorial 0.
*/
@Test
	public void whenCalculateFactorialForZeroThenOne() {
		Factorial fact = new Factorial();
		int result = fact.calc(0);
		int expected = 1;
		assertThat(result, is(expected));
	}
}