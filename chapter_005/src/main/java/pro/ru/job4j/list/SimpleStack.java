package pro.ru.job4j.list;

import java.util.Iterator;

/**
 * Class SimpleStack.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 25.06.2017
 */

/**
 * SimpleStack.
 *
 * @param <E> type.
 */
public class SimpleStack<E> implements Iterable<E> {
    /**
     * Контейнер.
     */
    private SimpleLinkedList<E> list = new SimpleLinkedList<E>();

    /**
     * Метод добавить.
     *
     * @param e элемент.
     * @return элемент.
     */
    public E push(E e) {
        list.add(e);
        return e;
    }

    /**
     * Возвращает последний элемент и удаляет его.
     *
     * @return элемент.
     */
    public E pop() {
        Object a = list.get(list.getSize() - 1);
        list.setLast(null);
        list.setSize(list.getSize() - 1);
        return (E) a;

    }

    /**
     * Итератор.
     *
     * @return итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return this.list.iterator();
    }
}
