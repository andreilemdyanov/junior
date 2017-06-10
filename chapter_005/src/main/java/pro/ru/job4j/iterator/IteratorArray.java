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
     * Метод для перехода внутри двумерного массива.
     */
    public void nextArray() {
        if (indexdeep == values[index].length && index <= values.length) {
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
        boolean result;
        if (values.length > index && values[index].length > indexdeep) {
            result = true;
        } else if (values.length - 1 == index && values[index].length > indexdeep) {
            result = true;
        } else if (values.length - 1 == index && values[index].length == indexdeep) {
            result = false;
        } else if (values.length > index && values[index].length == indexdeep) {
            result = true;
        } else {
            result = false;
        }
        return result;
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
