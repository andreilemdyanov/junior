package ru.job4j.additional;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class Bman.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 15.11.2017
 */
public class Bman {

    private ReentrantLock lock;
    int x;
    int y;

    public Bman(ReentrantLock lock, int x, int y) {
        this.lock = lock;
        this.x = x;
        this.y = y;
        this.doSome();
    }

    public void doSome() {
        try {
            lock.tryLock(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
