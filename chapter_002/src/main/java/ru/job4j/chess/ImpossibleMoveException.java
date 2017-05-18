package ru.job4j.chess;

/**
 * Class ImpossibleMoveException.
 *
 * @author Andrey Lemdyanov
 * @since 16.05.2017
 */
public class ImpossibleMoveException extends RuntimeException {
    /**
     * Конструктор.
     *
     * @param message сообщение об ошибке
     */
    public ImpossibleMoveException(String message) {
        super(message);
    }
}
