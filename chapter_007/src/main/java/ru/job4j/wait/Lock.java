package ru.job4j.wait;

/**
 * Class Lock.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 28.10.2017
 */
public class Lock {

    private volatile int a = 1;
    private volatile int b = 5;
    /**
     * Объект блокировки.
     */
    private final Object block = new Object();
    /**
     * Условие для блокировки.
     */
    private boolean condition = true;

    public void doSomething() throws InterruptedException {
        synchronized (block) {
            while (condition) {
                block.wait();
            }
            a = b + a + b;
        }
    }

    public void doIncrementB() {
        synchronized (block) {
            while (condition) {
                b = b + b;
                if (b > 25) {
                    condition = false;
                    block.notifyAll();
                }
            }
        }
    }


    public static void main(String[] args) {
        Lock lock = new Lock();

        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.doSomething();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.doIncrementB();
            }
        });

        a.start();
        b.start();

        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(lock.a);
    }
}
