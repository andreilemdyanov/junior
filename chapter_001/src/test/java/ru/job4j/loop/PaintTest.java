package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Paint Test.
*
* @author Andrey Lemdyanov (mailto:lemdyanov5@mail.ru)
* @version $Id$
* @since 0.1
*/
public class PaintTest {
/**
*Поле line для перехода на следующую строку.
*/
	private final String line = System.getProperty("line.separator");
/**
*Метод для отрисовки пирамиды высотой 2.
*/
@Test
public void whenPiramidWithHeightTwoThenStringWithTwoRows() {
        Paint paint = new Paint();
        String result = paint.piramid(2);
        String expected = String.format(" ^ %s^^^", line);
        assertThat(result, is(expected));
    }
/**
*Метод для отрисовки пирамиды высотой 3.
*/
@Test
 public void whenPiramidWithHeightThreeThenStringWithThreeRows() {
		Paint paint = new Paint();
        String result = paint.piramid(3);
        String expected = String.format("  ^  %s ^^^ %s^^^^^", line, line);
        assertThat(result, is(expected));
}
}