package ru.job4j.chess;

/**
 * Created by DNS on 12.05.2017.
 */
public class FigureNotFoundException extends RuntimeException {
    public FigureNotFoundException(String message) {
       super(message);
    }
}
