package ru.job4j.task5;

/**
 * Class StubInput.
 *
 * @author Andrey Lemdyanov
 * @since 27.04.2017
 */
public class StubInput implements Input {
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

    public int ask(String question, int[] range) {
        //      throw new UnsupportedOperationException("Unsupported operation");
        return -1;
    }
}