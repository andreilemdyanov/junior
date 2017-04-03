package ru.job4j.calculator;
/**
 * Class Calculator решение задачи части 001 урок 2.
 * @author Andrey Lemdyanov
 * @since 03.04.2017
*/

public class Calculator {
/**
* создание переменной result.
*/
	private double result;
/**
* Сложение.
* @param first первый аргумент.
* @param second второй аргумент.
* результат записываем в переменную result
*/
	public void add(double first, double second) {
		this.result = first + second;
	}
/**
* Вычитание.
* @param first первый аргумент.
* @param second второй аргумент.
* результат записываем в переменную result
*/
	public void substruct(double first, double second) {
		this.result = first - second;
	}
/**
* Деление.
* @param first первый аргумент.
* @param second второй аргумент.
* результат записываем в переменную result
*/
	public void div(double first, double second) {
		this.result = first / second;
	}
/**
* Умножение.
* @param first первый аргумент.
* @param second второй аргумент.
* результат записываем в переменную result
*/
	public void multiple(double first, double second) {
		this.result = first * second;
	}
/**
* Геттер.
* @return result результат.
*/
	public double getResult() {
		return this.result;
	}
}