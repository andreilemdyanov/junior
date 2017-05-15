package ru.job4j.chess;

public class Bishop extends Figure {

    public Bishop(int x, int y) {
        super(new Cell(x, y));
    }

    public Cell[] way(Cell dist) throws ImpossibleMoveException {
        Cell[] path = new Cell[8];
        int count = 0;
        if (dist.getX() <= 7 && dist.getY() <= 7 && dist.getX() >= 0 && dist.getY() >= 0) {
            if (Math.abs(this.position.getX() - dist.getX()) == Math.abs(this.position.getY() - dist.getY())) {
                int x = this.position.getX();
                int y = this.position.getY();
                do {
                    if (dist.getX() > this.position.getX() && dist.getY() > this.position.getY()) {
                        path[count++] = new Cell(x++, y++);
                    }
                    if (dist.getX() < this.position.getX() && dist.getY() < this.position.getY()) {
                        path[count++] = new Cell(x--, y--);
                    }
                    if (dist.getX() > this.position.getX() && dist.getY() < this.position.getY()) {
                        path[count++] = new Cell(x++, y--);
                    }
                    if (dist.getX() < this.position.getX() && dist.getY() > this.position.getY()) {
                        path[count++] = new Cell(x--, y++);
                    }
                } while (x != dist.getX() && y != dist.getY());
                path[count] = new Cell(dist.getX(), dist.getY());
            } else {
                throw new ImpossibleMoveException("Impossible move");
            }

        } else {
            throw new ImpossibleMoveException("Impossible move");
        }
        Cell[] pathnew = new Cell[count];
        System.arraycopy(path, 1, pathnew, 0, count);
        return pathnew;

    }

    public Figure clone(Cell dist) {
        return new Bishop(dist.getX(), dist.getY());
    }

}
