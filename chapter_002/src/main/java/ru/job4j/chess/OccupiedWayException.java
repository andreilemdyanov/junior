package ru.job4j.chess;

/**
 * Class OccupiedWayException.
 *
 * @author Andrey Lemdyanov
 * @since 16.05.2017
 */
public class OccupiedWayException extends RuntimeException {
    /**
     * Конструктор.
     *
     * @param message сообщение об ошибке
     */
    public OccupiedWayException(String message) {
        super(message);
    }
}
