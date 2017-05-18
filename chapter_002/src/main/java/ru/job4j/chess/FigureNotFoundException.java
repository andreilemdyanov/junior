package ru.job4j.chess;

/**
 * Class FigureNotFoundException.
 *
 * @author Andrey Lemdyanov
 * @since 16.05.2017
 */
public class FigureNotFoundException extends RuntimeException {
    /**
     * Конструктор.
     *
     * @param message Сообщение об ошибке
     */
    public FigureNotFoundException(String message) {
        super(message);
    }
}
