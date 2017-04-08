package ru.job4j.condition;
/**
 * Class Point решение задачи части 001 урок 3.
 * @author Andrey Lemdyanov
 * @since 08.04.2017
*/
public class Point {
/**
*Создаем переменную x.
*/
	private int x;
/**
*Создаем переменную y.
*/
	private int y;
/**
*Конструктор класса Point.
*Инициализирует поля.
*@param x первая координата.
*@param y вторая координата.
*/
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
/**
*Геттер поля x.
*@return значение поля x.
*/
	public int getX() {
		return this.x;
	}
/**
*Геттер поля y.
*@return значение поля y.
*/
	public int getY() {
		return this.y;
	}
/**
*Метод, проверяющий принадлежность функции.
*@param a первое число.
*@param b второе число.
*@return возвращает true, если точка находится на функции
*иначе возвращает false.
*/
	public boolean is(int a, int b) {
		return y == a * x + b;
	}
}
