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
     * @return четное?
     */
    public boolean checkEven() {
        return num[position] % 2 == 0;
    }

    /**
     * Переопределение hasNext.
     *
     * @return есть ли элемент?
     */
    @Override
    public boolean hasNext() {
        return num.length > position;
    }

    /**
     * Переопределение next.
     *
     * @return четное число.
     */
    @Override
    public Object next() {
        return num[position++];
    }
}