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
    private String text = "То, что мы  ,   легко!! можем??? отыскать... требуемый ключ x в дереве!, , , , , ";

    /**
     * Точка входа.
     *
     * @param args массив строк.
     */
    public static void main(String[] args) {
        CountWordsAndSpaces count = new CountWordsAndSpaces();
        System.out.println("Старт");
        new Thread() {
            @Override
            public void run() {
                String[] words = count.text.split("[\\s-,;:.!?]+");
                System.out.printf("Количество слов: %s\n", words.length);
            }
        }.start();

        new Thread() {
            @Override
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
        }.start();
        System.out.println("Финиш");
    }

}
