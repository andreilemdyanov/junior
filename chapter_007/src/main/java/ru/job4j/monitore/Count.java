package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class Count.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 31.08.2017
 */
@ThreadSafe
public class Count implements Runnable {
    /**
     * Значение счетчика.
     */
    @GuardedBy("this")
    private int value = 0;

    /**
     * Увеличивает значение на 1.
     */
    public void increment() {
        synchronized (this) {
            value++;
        }
    }

    /**
     * Метод запуска потока.
     */
    @Override
    public void run() {
        increment();
    }

    /**
     * Точка входа.
     *
     * @param args массив строк.
     * @throws InterruptedException исключение.
     */
    public static void main(String[] args) throws InterruptedException {
        Count count = new Count();
        Thread threadA = new Thread(count);
        Thread threadB = new Thread(count);
        Thread threadC = new Thread(count);
        threadA.start();
        threadA.join();
        threadB.start();
        threadB.join();
        threadC.start();
        threadC.join();

        System.out.println(count.value);

    }
}
