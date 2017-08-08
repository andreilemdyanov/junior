package ru.job4j.threads;

/**
 * Class CountChar.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 08.08.2017
 */
public class CountChar implements Runnable {
    /**
     * Строка.
     */
    private String string;
    /**
     * Количество символов.
     */
    private int count = 0;

    /**
     * Конструктор.
     *
     * @param string строка.
     */
    CountChar(String string) {
        this.string = string;
    }

    @Override
    public void run() {

        char[] chars = string.toCharArray();
        for (Character ch : chars) {
            if (!ch.equals(' ')) {
                count++;
            }
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Поток прерван на: " + this.count);
                return;
            }

        }
        System.out.println("Закончен на: " + this.count);

    }
}
