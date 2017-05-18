package ru.job4j.chess;

/**
 * Class Cell.
 *
 * @author Andrey Lemdyanov
 * @since 16.05.2017
 */
public class Cell {
    /**
     * Координата по горизонтали.
     */
    private int x;
    /**
     * Кооржината по вертикали.
     */
    private int y;

    /**
     * Конструктор.
     *
     * @param x координата
     * @param y координата
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Геттер горизонтальной координаты.
     *
     * @return координата x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Геттер вертикальной координаты.
     *
     * @return координата y
     */
    public int getY() {
        return this.y;
    }
}
