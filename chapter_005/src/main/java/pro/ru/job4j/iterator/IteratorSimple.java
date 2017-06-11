package pro.ru.job4j.iterator;

import java.util.Iterator;

/**
 * Class IteratorSimple.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 10.06.2017
 */
public class IteratorSimple implements Iterator {
    /**
     * Поле массив.
     */
    private final int[] values;
    /**
     * Позиция в массиве.
     */
    private int position = 0;
    /**
     * Индекс простого числа.
     */
    private int index;

    /**
     * Конструктор.
     *
     * @param values массив.
     */
    public IteratorSimple(int[] values) {
        this.values = values;
    }

    /**
     * Метод для проверки простое ли число.
     *
     * @return простое?
     */
    public int checkSimple() {
        for (int i = position; i < values.length; i++) {
            if (values[i] == 1 || values[i] == 2 || values[i] == 3) {
                position++;
                return i;
            }
            boolean flag = true;
            for (int j = 2; j < values[i]; j++) {
                if (values[i] % j == 0) {
                    position++;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                position++;
                return i;
            }
        }
        return -1;
    }

    /**
     * Переопределение hasNext.
     *
     * @return есть ли следующее значение?
     */
    @Override
    public boolean hasNext() {
        index = checkSimple();
        return index != -1;
    }

    /**
     * Переопределение next.
     *
     * @return значение.
     */
    @Override
    public Object next() {
        return values[index];
    }
}
