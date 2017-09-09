package ru.job4j.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import pro.ru.job4j.list.SimpleContainer;

import java.util.Iterator;

/**
 * Class ConcurrentSimpleLinkedList.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 10.09.2017
 */

/**
 * Class ConcurrentSimpleLinkedList.
 *
 * @param <E> параметризованный тип.
 */
@ThreadSafe
public class ConcurrentSimpleLinkedList<E> implements SimpleContainer<E> {
    /**
     * Объект для синхронизации.
     */
    @GuardedBy("this")
    /**
     * Поле первого элемента.
     */
    private ConcurrentSimpleLinkedList.Node<E> first = new ConcurrentSimpleLinkedList.Node<>(null);

    /**
     * Геттер первого нода.
     *
     * @return нод.
     */
    public ConcurrentSimpleLinkedList.Node<E> getFirst() {
        synchronized (this) {
            return first;
        }
    }

    /**
     * Сеттер первого нода.
     *
     * @param first новый нод.
     */
    public void setFirst(ConcurrentSimpleLinkedList.Node<E> first) {
        synchronized (this) {
            this.first = first;
        }
    }

    /**
     * Поле последнего элемента.
     */
    private ConcurrentSimpleLinkedList.Node<E> last = new ConcurrentSimpleLinkedList.Node<>(null);

    /**
     * Геттер last.
     *
     * @return last.
     */
    public ConcurrentSimpleLinkedList.Node<E> getLast() {
        synchronized (this) {
            return last;
        }
    }

    /**
     * Сеттер last.
     *
     * @param last новое значение.
     */
    public void setLast(ConcurrentSimpleLinkedList.Node<E> last) {
        synchronized (this) {
            this.last = last;
        }
    }

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
        synchronized (this) {
            return size;
        }
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
        private ConcurrentSimpleLinkedList.Node<E> next;
        /**
         * Ссылка на предыдущий.
         */
        private ConcurrentSimpleLinkedList.Node<E> previous;

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
    @Override
    public void add(E e) {
        synchronized (this) {
            ConcurrentSimpleLinkedList.Node<E> node = new ConcurrentSimpleLinkedList.Node<E>(e);

            if (first.element == null) {
                first = node;
                last = node;
            } else {
                last.next = node;
                node.previous = last;
                last = node;
                last.next = first;
            }
            size++;
        }
    }

    /**
     * Метод укорачивает лист на один объект с начала.
     */
    public void removeFirst() {
        synchronized (this) {
            if (size == 1 && this.getFirst() == this.getLast()) {
                this.setFirst(null);
                this.setLast(null);
                this.size = this.size - 1;
            } else {
                this.setFirst(this.getFirst().next);
                this.getFirst().previous = null;
                this.getLast().next = this.getFirst();
                this.size = this.size - 1;
            }

        }
    }

    /**
     * Метод укорачивает лист на один элемент с конца.
     *
     * @return удаленный элемент.
     */
    public E removeLast() {
        synchronized (this) {
            Object a = this.get(this.size - 1);
            this.last = null;
            this.size = this.size - 1;
            return (E) a;
        }
    }

    /**
     * Геттер элемента.
     *
     * @param index индекс.
     * @return элемент.
     */
    @Override
    public E get(int index) {
        synchronized (this) {
            Iterator it = this.iterator();
            int counter = 0;
            while (it.hasNext()) {

                Object a = it.next();
                if (counter == index) {
                    return (E) a;
                }
                counter++;
            }
            return null;
        }
    }

    /**
     * Итератор.
     *
     * @return итератор.
     */
    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            private ConcurrentSimpleLinkedList.Node<E> currentNode = first;
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
                return currentNode.element != null && size > count;
            }
        };
        return it;
    }
}
