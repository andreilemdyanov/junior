package ru.job4j.task5;

/**
 * Class StubInput.
 *
 * @author Andrey Lemdyanov
 * @since 27.04.2017
 */
public class StubInput extends ConsoleInput implements Input {
    /**
     * Массив ответов.
     */
    private String[] answers;
    /**
     * Счетчик.
     */
    private int position = 0;

    /**
     * Конструктор.
     *
     * @param answers массив ответов.
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Метод возвращает значения массива в цикле.
     *
     * @param question вопрос.
     * @return значение массива.
     */
    public String ask(String question) {
        return answers[position++];
    }

    /**
     * Метод спросить.
     * @param question вопрос.
     * @param range массив вариантов.
     * @return -1.
     */
    public int ask(String question, int[] range) {
        //      throw new UnsupportedOperationException("Unsupported operation");
//        return -1;
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        }
        while (invalid);
        return value;
    }
}