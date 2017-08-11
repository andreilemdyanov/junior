package ru.job4j.jmm;

/**
 * Class Visible.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 11.07.2017
 */
public class Visible {
    /**
     * Счетчик.
     */
    private
    //volatile
            int count = 0;
    /**
     * Флаг.
     */
    private boolean isStop = false;

    /**
     * Точка входа.
     *
     * @param args массив строк.
     * @throws InterruptedException если поток прерван.
     */
    public static void main(String[] args) throws InterruptedException {
        Visible counter = new Visible();
        Thread threadFirst = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!counter.isStop) {
                    counter.count++;
                }
            }
        });
        Thread threadSecond = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!counter.isStop) {
                    System.out.println(counter.count);
                }
            }
        });
        threadFirst.start();
        threadSecond.start();
        Thread.sleep(100);
        counter.isStop = true;
    }
}
