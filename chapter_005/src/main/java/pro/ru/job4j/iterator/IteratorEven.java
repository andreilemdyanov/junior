package pro.ru.job4j.iterator;

import java.util.Iterator;

/**
 * Class IteratorEven.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 09.06.2017
 */
public class IteratorEven implements Iterator {
    /**
     * Поле массива.
     */
    private final int[] num;
    /**
     * Позиция в массиве.
     */
    private int position = 0;
    /**
     * Индекс четного числа.
     */
    private int index;

    /**
     * Конструктор.
     *
     * @param n массив.
     */
    public IteratorEven(int[] n) {
        num = n;
    }

    /**
     * Метод проверяет четное ли число.
     *
     * @return индекс четного числа или -1 если их нет.
     */
    public int checkEven() {
        for (int i = position; i < num.length; i++) {
            position++;
            if (num[i] % 2 == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Переопределение hasNext.
     *
     * @return есть ли элемент?
     */
    @Override
    public boolean hasNext() {
        index = checkEven();
        return index != -1;
    }

    /**
     * Переопределение next.
     *
     * @return четное число.
     */
    @Override
    public Object next() {
        return num[index++];
    }
}