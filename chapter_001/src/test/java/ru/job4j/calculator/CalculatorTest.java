package ru.job4j.calculator;

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
public class CalculatorTest {
/**
*Test addition.
*/
@Test
	public void whenAddOnePlusOneThenTwo() {
		Calculator calc = new Calculator();
		calc.add(1D, 1D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}
/**
*Test subtraction.
*/
@Test
	public void whenSubstructTwoMinusOneThenOne() {
		Calculator calc = new Calculator();
		calc.substruct(2D, 1D);
		double result = calc.getResult();
		double expected = 1D;
		assertThat(result, is(expected));
	}
/**
*Test division.
*/
@Test
	public void whenDivFourDivisionTwoThenTwo() {
		Calculator calc = new Calculator();
		calc.div(4D, 2D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}
/**
*Test multiplication.
*/
@Test
	public void whenMultipleTwoMultiplicationThreeThenSix() {
		Calculator calc = new Calculator();
		calc.multiple(2D, 3D);
		double result = calc.getResult();
		double expected = 6D;
		assertThat(result, is(expected));
	}
}