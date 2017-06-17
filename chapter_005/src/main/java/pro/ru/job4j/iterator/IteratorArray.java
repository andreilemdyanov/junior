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
     * Геттер индекса.
     *
     * @return индекс.
     */
    public int getIndex() {
        return index;
    }

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
     * Метод для перехода внутри двумерного массива.
     */
    public void nextArray() {
        if (indexdeep == values[index].length && index < values.length - 1) {
            index++;
            indexdeep = 0;
        }
    }

    /**
     * Переопределение метода hasNext.
     *
     * @return есть ли еще элемент?
     */
    @Override
    public boolean hasNext() {
        return (values.length - 1 >= index && values[index].length - 1 >= indexdeep)
                || (values.length - 1 >= this.getIndex() + 1 && values[this.getIndex() + 1].length - 1 >= 0);

    }

    /**
     * Переопределение метода next.
     *
     * @return значение текущего элемента и перевод каретки на следующий.
     */
    @Override
    public Object next() {
        nextArray();
        return values[index][indexdeep++];
    }
}
