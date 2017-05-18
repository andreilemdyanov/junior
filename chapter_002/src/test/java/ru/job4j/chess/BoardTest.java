package ru.job4j.chess;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Class BoardTest.
 *
 * @author Andrey Lemdyanov
 * @since 19.05.2017
 */

public class BoardTest {
    /**
     * Тест исключения.
     */
    @Test(expected = FigureNotFoundException.class)
    public void whenFigureNotFoundOnBoard() {
        Figure[] figure = new Figure[]{new Bishop(2, 0)};
        Board board = new Board(figure);
        board.move(new Cell(3, 1), new Cell(4, 4));
    }

    /**
     * Тест исключения.
     */
    @Test(expected = ImpossibleMoveException.class)
    public void whenFigureImpossibleMove() {
        Figure[] figure = new Figure[]{new Bishop(2, 0)};
        Board board = new Board(figure);
        board.move(new Cell(2, 0), new Cell(4, 4));
    }

    /**
     * Тест исключения.
     */
    @Test(expected = OccupiedWayException.class)
    public void whenFigureWayIsOccupied() {
        Figure[] figure = new Figure[]{new Bishop(2, 0), new Bishop(5, 3)};
        Board board = new Board(figure);
        board.move(new Cell(2, 0), new Cell(6, 4));
    }

    /**
     * Тест верного хода.
     */
    @Test
    public void whenFigureMoveThenTrue() {
        Figure[] figure = new Figure[]{new Bishop(2, 0)};
        Board board = new Board(figure);
        boolean result = board.move(new Cell(2, 0), new Cell(5, 3));
        assertTrue("Ok", result);
    }
}
