package ru.job4j.condition;
/**
 * Class Triangle решение задачи части 001 урок 3.
 * @author Andrey Lemdyanov
 * @since 10.04.2017
*/
public class Triangle {
/**
*Создаем переменную "a" для хранения ссылки типа Point.
*/
	private Point a;
/**
*Создаем переменную "b" для хранения ссылки типа Point.
*/
	private Point b;
/**
*Создаем переменную "c" для хранения ссылки типа Point.
*/
	private Point c;
/**
*Конструктор класса.
*@param a первая точка
*@param b вторая точка
*@param c третья точка
*/
	public Triangle(Point a, Point b, Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
/**
*Метод для подсчета площади треугольника.
*@return результат типа double
*/
	public double area() {
		return Math.abs(0.5 * ((a.getX() - c.getX()) * (b.getY() - c.getY()) - (b.getX() - c.getX()) * (a.getY() - c.getY())));
	}
}