package ru.job4j.chess;

public abstract class Figure implements Cloneable {
    final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    public Cell getPosition() {
        return this.position;
    }

    abstract Figure clone(Cell dist);

    abstract Cell[] way(Cell dist) throws ImpossibleMoveException;
}
