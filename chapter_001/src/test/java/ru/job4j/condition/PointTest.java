package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Point Test.
*
* @author Andrey Lemdyanov (mailto:lemdyanov5@mail.ru)
* @version $Id$
* @since 0.1
*/
public class PointTest {
/**
*Test true.
*/
@Test
public void whenPointKeepFunction() {
	Point one = new Point(3, 10);
	boolean result = one.is(2, 4);
	boolean expected = true;
	assertThat(result, is(expected));
}
/**
*Test false.
*/
@Test
public void whenPointNotKeepFunction() {
	Point one = new Point(5, 7);
	boolean result = one.is(7, 9);
	boolean expected = false;
	assertThat(result, is(expected));
}

}