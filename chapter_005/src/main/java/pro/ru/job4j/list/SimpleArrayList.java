package pro.ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Class SimpleArrayList.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 22.06.2017
 */

/**
 * SimpleArrayList.
 *
 * @param <E> type.
 */
public class SimpleArrayList<E> implements SimpleContainer<E> {
    /**
     * Массив.
     */
    private Object[] container;

    /**
     * Геттер массива.
     *
     * @return массив.
     */
    public E[] getContainer() {
        return (E[]) container;
    }

    /**
     * Позиция.
     */
    private int position = 0;

    /**
     * Конструктор.
     */
    public SimpleArrayList() {
        container = new Object[10];
    }

    /**
     * Геттер элемента по индексу.
     *
     * @param index индекс.
     * @return элемент.
     */
    @Override
    public E get(int index) {
        return (E) container[index];
    }

    /**
     * Добавление элемента.
     *
     * @param o элемент.
     */
    @Override
    public void add(E o) {
        if (position == container.length) {
            Object[] temp = new Object[container.length * 2];
            System.arraycopy(container, 0, temp, 0, container.length);
            container = temp;

        }
        container[position++] = o;

    }

    /**
     * Итератор.
     *
     * @return итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < container.length;
            }

            @Override
            public E next() {
                return (E) container[index++];
            }
        };
    }

    /**
     * Переопределение equals.
     *
     * @param o элемент.
     * @return да/нет.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SimpleArrayList<?> that = (SimpleArrayList<?>) o;

        if (position != that.position) {
            return false;
        }
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(container, that.container);
    }

    /**
     * Переопределение hashcode.
     *
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        int result = Arrays.hashCode(container);
        result = 31 * result + position;
        return result;
    }
}
