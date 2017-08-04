package ru.job4j.threads;

/**
 * Class CountWordsAndSpaces.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 29.07.2017
 */
public class CountWordsAndSpaces {
    /**
     * Текст.
     */
    private String text = "То, что мы  ,   легко!! можем??? отыскать... требуемый    Необходимо, что бы вначале запуска программы из первой части на экране выводилась информация о программа. А после вычисления данных, выводилась запись о завершении программы. Важно, эта информация всегда должна выводиться в начале и в конце вычисления.     dkfkf fk khk d dgkf,,,,, ключ x в дереве!, , , , , ";

    /**
     * Точка входа.
     *
     * @param args массив строк.
     */
    public static void main(String[] args) {
        CountWordsAndSpaces count = new CountWordsAndSpaces();

        Thread wordsThread = new Thread(new Runnable() {
            public void run() {
                String[] words = count.text.split("[\\s-,;:.!?]+");
                System.out.printf("Количество слов: %s\n", words.length);

            }
        });

        Thread spacesThread = new Thread(new Runnable() {
            public void run() {
                int spaces = 0;
                char[] string = count.text.toCharArray();
                for (Character ch : string) {
                    if (ch.equals(' ')) {
                        spaces++;
                    }
                }
                System.out.printf("Количество пробелов: %s\n", spaces);
            }
        });

        System.out.println("Старт");
        wordsThread.start();
        spacesThread.start();
        try {
            wordsThread.join();
            spacesThread.join();
            Thread.sleep(1000);
            wordsThread.interrupt();
            spacesThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Финиш");
    }

}
