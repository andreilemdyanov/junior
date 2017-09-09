package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import pro.ru.job4j.list.SimpleContainer;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Class ConcurrentSimpleArrayList.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 10.09.2017
 */

/**
 * Class ConcurrentSimpleArrayList.
 *
 * @param <E> параметризованый тип.
 */
@ThreadSafe
public class ConcurrentSimpleArrayList<E> implements SimpleContainer<E> {
    /**
     * Объект для синхронизации.
     */
    @GuardedBy("this")

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
        synchronized (this) {
            return (E[]) container;
        }
    }

    /**
     * Позиция.
     */
    private int position = 0;

    /**
     * Конструктор.
     */
    public ConcurrentSimpleArrayList() {
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
        synchronized (this) {
            return (E) container[index];
        }
    }

    /**
     * Добавление элемента.
     *
     * @param o элемент.
     */
    @Override
    public void add(E o) {
        synchronized (this) {
            if (position == container.length) {
                Object[] temp = new Object[container.length * 2];
                System.arraycopy(container, 0, temp, 0, container.length);
                container = temp;

            }
            container[position++] = o;
        }
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
        synchronized (this) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            ConcurrentSimpleArrayList<?> that = (ConcurrentSimpleArrayList<?>) o;

            if (position != that.position) {
                return false;
            }
            // Probably incorrect - comparing Object[] arrays with Arrays.equals
            return Arrays.equals(container, that.container);
        }
    }

    /**
     * Переопределение hashcode.
     *
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        synchronized (this) {
            int result = Arrays.hashCode(container);
            result = 31 * result + position;
            return result;
        }
    }
}
