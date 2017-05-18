package ru.job4j.chess;

/**
 * Class Board.
 *
 * @author Andrey Lemdyanov
 * @since 16.05.2017
 */
public class Board {
    /**
     * Массив фигур.
     */
    Figure[] figures;

    /**
     * Конструктор.
     *
     * @param f массив фигур
     */
    public Board(Figure[] f) {
        this.figures = f;
    }

    /**
     * Метод для нахождения фигуры в массиве.
     *
     * @param cell клетка.
     * @return фигура
     */
    public Figure findByCell(Cell cell) {
        Figure f = null;
        for (Figure figure : figures) {
            if (figure.getPosition().getX() == cell.getX() && figure.getPosition().getY() == cell.getY()) {
                f = figure;
            }
        }
        return f;
    }

    /**
     * Метод для хождения фигуры.
     *
     * @param source начальная точка пути
     * @param dist   конечная точка пути
     * @return сделан ли ход
     * @throws ImpossibleMoveException если фигура так не ходит
     * @throws OccupiedWayException    если путь занят другой фигурой
     * @throws FigureNotFoundException если фигура не найдена
     */
    boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Figure need;
        Figure figure = this.findByCell(source);
        if (figure == null) {
            throw new FigureNotFoundException("Figure not found");
        }
        if (source.getX() == figure.getPosition().getX() && source.getY() == figure.getPosition().getY()) {
            Cell[] path = figure.way(dist);
            for (Figure figur : figures) {
                for (Cell cell : path) {
                    if (figur.getPosition().getX() == cell.getX() && figur.getPosition().getY() == cell.getY()) {
                        throw new OccupiedWayException("Way is occupied");
                    }

                }

            }
            need = figure.clone(dist);
            System.out.println(need.getPosition().getX() + " " + need.getPosition().getY());
        }
        return true;
    }
}
