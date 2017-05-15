package ru.job4j.chess;

public class Board {
    Figure[] figures;

    public Board(Figure[] f) {
        this.figures = f;
    }

    boolean move(Cell source, Cell dist) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        Figure need;
        int count = 0;
        for (Figure figure : figures) {
            count++;
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
                break;
            } else if (count >= figures.length) {
                throw new FigureNotFoundException("Figure not found");
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Figure[] f = new Figure[]{new Bishop(2, 0), new Bishop(5, 3)};
        Board board = new Board(f);
        board.move(new Cell(2, 3), new Cell(7, 5));

    }
}
