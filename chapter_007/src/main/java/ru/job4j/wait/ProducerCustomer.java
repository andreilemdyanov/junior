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
 *
 * @param <T> параметризованный тип.
 */
public class ProducerCustomer<T> {

    /**
     * Очередь.
     */
    private final SimpleQueue<T> queue = new SimpleQueue<>();

    /**
     * Размер очереди.
     *
     * @return размер.
     */
    public int size() {
        return queue.size();
    }

    /**
     * Очередь пуста?
     *
     * @return да/нет.
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Добавление элемента в очередь.
     *
     * @param a элемент.
     */
    public void put(T a) {
        synchronized (queue) {
            queue.push(a);
            if (!this.isEmpty()) {
                queue.notify();
            }
        }
    }

    /**
     * Выталкивание элемента из очереди.
     *
     * @return элемент.
     */
    public T take() {
        synchronized (queue) {
            if (this.isEmpty()) {
                try {
                    queue.wait(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return queue.pop();
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
