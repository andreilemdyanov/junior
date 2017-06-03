package pro.ru.job4j.iterator;

import java.util.Iterator;

/**
 * Class IteratorArray.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 03.06.2017
 */
public class IteratorArray implements Iterator {
    /**
     * Поле двухмерного массива.
     */
    private final int[][] values;
    /**
     * Поле индекс - количество массивов в массиве.
     */
    private int index = 0;
    /**
     * Поле количества элементов в массиве.
     */
    private int indexdeep = 0;

    /**
     * Конструктор.
     *
     * @param values двухмерный массив.
     */
    public IteratorArray(final int[][] values) {
        this.values = values;
    }

    /**
     * Переопределение метода hasNext.
     *
     * @return есть ли еще элемент?
     */
    @Override
    public boolean hasNext() {
        if (indexdeep == values[index].length) {
            index++;
            indexdeep = 0;
        }
        return values.length > index;
    }

    /**
     * Переопределение метода next.
     *
     * @return значение текущего элемента и перевод каретки на следующий.
     */
    @Override
    public Object next() {
        return values[index][indexdeep++];
    }
}
