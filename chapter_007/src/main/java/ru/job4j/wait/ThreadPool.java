package ru.job4j.wait;

/**
 * Class ThreadPool.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 28.10.2017
 */
public class ThreadPool {
    /**
     * Потокобезопасная очередь.
     */
    private final ProducerCustomer<Work> thpool = new ProducerCustomer<>();
    /**
     * Массив потоков.
     */
    private final Thread[] pool = new Thread[Runtime.getRuntime().availableProcessors()];
    /**
     * Переменная для вычислений.
     */
    private volatile long i = 1;

    /**
     * Добавить элемент в очередь.
     *
     * @param work работа.
     */
    public void add(Work work) {
        thpool.put(work);
    }

    /**
     * Метод для выполнения потоков.
     *
     * @throws InterruptedException исключение.
     */
    public void init() throws InterruptedException {
        while (!thpool.isEmpty()) {
            for (int j = 0; j < pool.length; j++) {
                pool[j] = new Thread(thpool.take());
                pool[j].start();
                pool[j].join();
            }
        }
    }

    /**
     * Класс работа.
     */
    private final class Work implements Runnable {

        @Override
        public void run() {
            i = (i + i) * i;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool p = new ThreadPool();
        p.add(p.new Work());
        p.add(p.new Work());
        p.add(p.new Work());
        p.add(p.new Work());
        p.add(p.new Work());
        p.init();
        System.out.println(p.i);
    }
}