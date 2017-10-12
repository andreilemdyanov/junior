package ru.job4j.wait;

import pro.ru.job4j.list.SimpleQueue;

/**
 * ProducerCustomer.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 12.10.2017
 */

/**
 * ProducerCustomer.
 * @param <T> параметризованный тип.
 */
public class ProducerCustomer<T> {
    /**
     * Объект для блокировки.
     */
    private final Object lock = new Object();
    /**
     * Очередь.
     */
    private SimpleQueue<T> queue = new SimpleQueue<>();
    /**
     * Условие для блокировки.
     */
    private boolean condition = true;

    /**
     * Добавление элемента в очередь.
     *
     * @param a элемент.
     */
    public void put(T a) {
        synchronized (lock) {
            System.out.printf("%s добавление в очередь\n", Thread.currentThread().getId());
            queue.push(a);
            if (!condition) {
                System.out.printf("%s блокировка снята\n", Thread.currentThread().getId());
                lock.notify();
                condition = true;
            }
        }
    }

    /**
     * Выталкивание элемента из очереди.
     *
     * @return элемент.
     */
    public T take() {
        synchronized (lock) {
            if (queue.size() == 0) {
                System.out.printf("%s смена условия на false\n", Thread.currentThread().getId());
                condition = false;
                try {
                    System.out.printf("%s блокировка\n", Thread.currentThread().getId());
                    lock.wait(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T a = queue.pop();
            if (condition) {
                System.out.printf("%s удаление из очереди\n", Thread.currentThread().getId());
            }
            return a;
        }
    }

    /**
     * Точка входа.
     *
     * @param args массив строк.
     */
    public static void main(String[] args) {
        ProducerCustomer<Integer> pr = new ProducerCustomer<>();
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                pr.put(2);
                pr.put(4);
                pr.put(66);
            }
        });
        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(pr.take());
                System.out.println(pr.take());
                System.out.println(pr.take());
                pr.put(4);
                System.out.println(pr.take());
                System.out.println(pr.take());
                pr.put(4);
            }
        });
        b.start();
        a.start();
    }
}
