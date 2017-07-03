package pro.ru.job4j.set;

import java.util.Iterator;

/**
 * Class SimpleLinkedSet.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 01.07.2017
 */

/**
 * SimpleLinkedSet.
 *
 * @param <E> type.
 */
public class SimpleLinkedSet<E> {
    /**
     * Поле первого элемента.
     */
    private SimpleLinkedSet.Node<E> first = new SimpleLinkedSet.Node<>(null);

    /**
     * Поле последнего элемента.
     */
    private SimpleLinkedSet.Node<E> last = new SimpleLinkedSet.Node<>(null);

    /**
     * Размер контейнера.
     */
    private int size = 0;

    /**
     * Геттер size.
     *
     * @return size.
     */
    public int getSize() {
        return size;
    }

    /**
     * Вложенный статический класс.
     *
     * @param <E> тип параметра.
     */
    private static class Node<E> {
        /**
         * Значение.
         */
        private E element;
        /**
         * Ссылка на следующий элемент.
         */
        private SimpleLinkedSet.Node<E> next;
        /**
         * Ссылка на предыдущий.
         */
        private SimpleLinkedSet.Node<E> previous;

        /**
         * Конструктор.
         *
         * @param element элемент.
         */
        Node(E element) {
            this.element = element;
        }

        /**
         * Переопределение toString.
         *
         * @return строка.
         */
        @Override
        public String toString() {
            return "Node{"
                    + "element="
                    + element
                    + '}';
        }
    }

    /**
     * Добавить элемент.
     *
     * @param e элемент.
     */
    public void add(E e) {
        SimpleLinkedSet.Node<E> node = new SimpleLinkedSet.Node<E>(e);
        if (first.element == null) {
            first = node;
            last = node;
            size++;
        }
        Iterator it = this.iterator();
        boolean result = false;
        while (it.hasNext()) {
            if (e.equals(it.next())) {
                result = true;
                break;
            }
        }
        if (!result) {
            last.next = node;
            node.previous = last;
            last = node;
            last.next = first;
            size++;
        }
    }

    /**
     * Итератор.
     *
     * @return итератор.
     */
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            private SimpleLinkedSet.Node<E> currentNode = first;
            private int count = 0;

            @Override
            public E next() {
                Object a = currentNode.element;
                currentNode = currentNode.next;
                count++;
                return (E) a;
            }

            @Override
            public boolean hasNext() {
                return currentNode != null && size > count;
            }
        };
        return it;
    }

}
