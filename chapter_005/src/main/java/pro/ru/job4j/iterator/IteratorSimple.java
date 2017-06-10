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
    public boolean checkSimple() {
        boolean flag = true;
        if (values[position] == 1 || values[position] == 2 || values[position] == 3) {
            flag = true;
        }
        for (int i = 2; i < values[position]; i++) {
            if (values[position] % i == 0) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Переопределение hasNext.
     *
     * @return есть ли следующее значение?
     */
    @Override
    public boolean hasNext() {
        return values.length > position;
    }

    /**
     * Переопределение next.
     *
     * @return значение.
     */
    @Override
    public Object next() {
        return values[position++];
    }
}
