package ru.job4j.task5;

/**
 * Class ValidateInput.
 *
 * @author Andrey Lemdyanov
 * @since 10.05.2017
 */

public class ValidateInput extends ConsoleInput {
    /**
     * Переопределение метода ask.
     *
     * @param question строка.
     * @param range    массив допустимых значений.
     * @return value.
     */
    public int ask(String question, int[] range) {
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
