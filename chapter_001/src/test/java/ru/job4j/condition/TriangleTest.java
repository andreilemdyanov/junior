package ru.job4j.condition;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;
/**
* Triangle Test.
*
* @author Andrey Lemdyanov (mailto:lemdyanov5@mail.ru)
* @version $Id$
* @since 0.1
*/
public class TriangleTest {
/**
*Test area.
*/
@Test
public void whenCalculateArea() {
	Point a = new Point(3, 5);
	Point b = new Point(4, 8);
	Point c = new Point(6, 2);
	Triangle tri = new Triangle(a, b, c);
	double result = tri.area();
	double expected = 6D;
	assertThat(result, closeTo(expected, 0.01));
}
}