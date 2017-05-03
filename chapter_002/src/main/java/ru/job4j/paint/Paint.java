package ru.job4j.paint;

/**
 * Class Paint.
 *
 * @author Andrey Lemdyanov
 * @since 03.05.2017
 */
public class Paint {
    /**
     * Ссылочная переменная типа Shape.
     */
    Shape shape;

    /**
     * Метод рисует форму.
     *
     * @param shape объект реализующий интерфейс Shape.
     */
    public void draw(Shape shape) {
        this.shape = shape;
        System.out.print(shape.pic());
    }

    public static void main(String[] args) {
        new Paint().draw(new Square());
    }

}
