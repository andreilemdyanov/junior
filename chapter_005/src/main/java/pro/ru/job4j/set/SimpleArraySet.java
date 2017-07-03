package pro.ru.job4j.set;

import java.util.Iterator;

/**
 * Class SimpleArraySet.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 01.07.2017
 */

/**
 * SimpleArraySet.
 *
 * @param <E> type.
 */
public class SimpleArraySet<E> implements Iterable<E> {
    /**
     * Контейнер массив.
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
    public SimpleArraySet() {
        container = new Object[10];
    }

    /**
     * Добавление элемента.
     *
     * @param o элемент.
     */
    public void add(E o) {
        if (position == container.length) {
            Object[] temp = new Object[container.length * 2];
            System.arraycopy(container, 0, temp, 0, container.length);
            container = temp;

        }
        if (!this.checkSame(o)) {
            container[position++] = o;
        }

    }

    /**
     * Метод проверяет есть ли такой же элемент.
     *
     * @param o элемент.
     * @return да/нет
     */
    public boolean checkSame(E o) {
        boolean flag = false;
        for (Object a : container) {
            if (o.equals(a)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Итератор.
     *
     * @return итератор.
     */
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
}
