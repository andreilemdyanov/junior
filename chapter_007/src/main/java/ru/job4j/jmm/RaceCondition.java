package ru.job4j.jmm;

/**
 * Class RaceCondition.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 11.08.2017
 */
public class RaceCondition {
    /**
     * Счетчик.
     */
    private int count = 0;
    /**
     * Флаг.
     */
    private boolean isStop = false;

    /**
     * Точка входа.
     *
     * @param args массив строк.
     * @throws InterruptedException если прерван поток.
     */
    public static void main(String[] args) throws InterruptedException {
        RaceCondition counter = new RaceCondition();
        Thread threadFirst = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!counter.isStop) {
//                    synchronized (this) {
                    counter.count++;
//                    }
                }
            }
        });
        Thread threadSecond = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!counter.isStop) {
//                    synchronized (this) {
                    if (counter.count % 2 == 0) {
                        System.out.printf("Четное значение count: %s\n", counter.count);
//                        }
                    }
                }
            }
        });
        Thread threadThird = new Thread(new Runnable() {
            @Override
            public void run() {
//                synchronized (this) {
                counter.count++;
//                }
            }
        });
        threadFirst.start();
        threadThird.start();
        threadSecond.start();
        Thread.sleep(100);
        counter.isStop = true;
    }
}
