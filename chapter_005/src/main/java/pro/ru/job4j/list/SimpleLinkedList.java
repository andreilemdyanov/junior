package pro.ru.job4j.list;

import java.util.Iterator;

/**
 * Class SimpleLinkedList.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.06.2017
 */
public class SimpleLinkedList<E> implements SimpleContainer<E> {
    /**
     * Поле первого элемента.
     */
    private Node<E> first = new SimpleLinkedList.Node<>(null);

    /**
     * Поле последнего элемента.
     */
    private Node<E> last = new SimpleLinkedList.Node<>(null);

    /**
     * Геттер last.
     *
     * @return last.
     */
    public Node<E> getLast() {
        return last;
    }

    /**
     * Сеттер last.
     *
     * @param last новое значение.
     */
    public void setLast(Node<E> last) {
        this.last = last;
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
        return size;
    }

    /**
     * Сеттер size.
     *
     * @param size новое значение.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Вложенный статический класс.
     *
     * @param <E> тип параметра.
     */
    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

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
            return "Node{" +
                    "element=" + element +
                    '}';
        }
    }

    /**
     * Добавить элемент.
     *
     * @param e элемент.
     */
    @Override
    public void add(E e) {
        Node<E> node = new SimpleLinkedList.Node<E>(e);

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

    /**
     * Геттер элемента.
     *
     * @param index индекс.
     * @return элемент.
     */
    @Override
    public E get(int index) {
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

    /**
     * Итератор.
     *
     * @return итератор.
     */
    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            Node<E> currentNode = first;
            int count = 0;

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


    public static void main(String[] args) {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println(list.first.next);
        System.out.println(list.first.previous);
        System.out.println(it.hasNext());
        System.out.println(list.get(2));
        System.out.println(list.get(1));
        System.out.println(list.get(4));
        System.out.println(list.get(7));
    }
}
