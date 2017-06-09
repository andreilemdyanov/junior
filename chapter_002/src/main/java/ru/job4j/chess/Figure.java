package ru.job4j.chess;

/**
 * Abstract Class Figure.
 *
 * @author Andrey Lemdyanov
 * @since 16.05.2017
 */
public abstract class Figure implements Cloneable {
    /**
     * Позиция фигуры.
     */
    private final Cell position;


    /**
     * Конструктор.
     *
     * @param position позиция.
     */
    public Figure(Cell position) {
        this.position = position;
    }

    /**
     * Геттер позиции фигуры.
     *
     * @return клетка
     */
    public Cell getPosition() {
        return this.position;
    }

    /**
     * Метод для создания объекта.
     *
     * @param dist позиция
     * @return объект
     */
    abstract Figure clone(Cell dist);

    /**
     * Метод для вычисления хода.
     *
     * @param dist конечная точка
     * @return массив шагов
     * @throws ImpossibleMoveException если ход не возможен
     */
    abstract Cell[] way(Cell dist) throws ImpossibleMoveException;
}
