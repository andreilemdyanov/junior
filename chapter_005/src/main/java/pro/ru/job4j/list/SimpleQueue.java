package pro.ru.job4j.list;

import java.util.Iterator;

/**
 * Class SimpleQueue.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 28.06.2017
 */
public class SimpleQueue<E> implements Iterable<E> {
    /**
     * Поле лист.
     */
    private SimpleLinkedList<E> list = new SimpleLinkedList<E>();

    /**
     * Добавление.
     *
     * @param e элемент.
     * @return элемент.
     */
    public E push(E e) {
        list.add(e);
        return e;
    }

    /**
     * Выводит первый элемент и удаляет его.
     *
     * @return элемент.
     */
    public E pop() {
        Object a = null;
        if (list.getSize() > 0) {
            a = list.get(0);
            list.step();
            if (list.getSize() == 0) {
                list = new SimpleLinkedList<E>();

            }
        } else {
            list = new SimpleLinkedList<E>();
        }
        return (E) a;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    public static void main(String[] args) {
        SimpleQueue<Integer> n = new SimpleQueue<>();
        n.push(1);
        n.push(5);
        n.push(9);
        n.push(8);
        System.out.println(n.pop());
        System.out.println(n.pop());
        System.out.println(n.pop());
        System.out.println(n.pop());
        System.out.println(n.pop());
        n.push(7);
        System.out.println(n.pop());
        System.out.println(n.pop());
        System.out.println(n.pop());
        n.push(7);
        n.push(66);
        n.push(56);
        System.out.println(n.pop());
        System.out.println(n.pop());
        System.out.println(n.pop());


    }
}
