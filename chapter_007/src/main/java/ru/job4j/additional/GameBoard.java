package ru.job4j.additional;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class GameBoard.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 15.11.2017
 */
public class GameBoard {

    private final ReentrantLock[][] board = new ReentrantLock[100][100];
    private Bman bman;

    public GameBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
        bman = new Bman(board[0][0], 0, 0);
        bman.doSome();
    }


    private void move() {
        Random r = new Random();
        if (r.nextBoolean()) {
            if (r.nextBoolean()) {
                bman.x -= 1;
            } else {
                bman.x += 1;
            }
        } else {
            if (r.nextBoolean()) {
                bman.y -= 1;
            } else {
                bman.y += 1;
            }
        }
        this.createBman();
    }

    private void createBman() {
        if (bman.x == board.length) {
            bman.x = bman.x - 2;
        } else if (bman.x == -1) {
            bman.x = bman.x + 2;
        } else if (bman.y == board.length) {
            bman.y = bman.y - 2;
        } else if (bman.y == -1) {
            bman.y = bman.y + 2;
        }
        if (bman.x >= 0 && bman.y >= 0 && bman.x < board.length && bman.y < board.length) {
            bman = new Bman(board[bman.x][bman.y], bman.x, bman.y);
            bman.doSome();
        }
        System.out.printf("Bman - %s : %s\n", bman.x, bman.y);
    }

    public static void main(String[] args) {
        GameBoard board = new GameBoard();
        Thread a = new Thread(() -> {
            while (true) {
                board.move();
            }
        });
        a.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}