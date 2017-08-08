package ru.job4j.threads;

/**
 * Class Time.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 08.08.2017
 */
public class Time implements Runnable {
    /**
     * Время работы потока.
     */
    private long time;

    /**
     * Конструктор.
     *
     * @param time время работы.
     */
    public Time(long time) {
        this.time = time;
    }

    @Override
    public void run() {
        long before = System.currentTimeMillis();
        long after;
        do {
            after = System.currentTimeMillis() - before;

        } while (this.time > after);
        Thread.currentThread().interrupt();
    }
}

