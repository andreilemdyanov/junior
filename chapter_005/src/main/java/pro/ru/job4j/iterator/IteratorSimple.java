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
                return i;
            }
            boolean flag = true;
            for (int j = 2; j < values[i]; j++) {
                if (values[i] % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
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
        return checkSimple() != -1;
    }

    /**
     * Переопределение next.
     *
     * @return значение.
     */
    @Override
    public Object next() {
        position = checkSimple();
        return values[position++];
    }
}
