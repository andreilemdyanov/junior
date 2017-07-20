package pro.ru.job4j.list;

/**
 * Class SimpleQueue.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 28.06.2017
 */

/**
 * SimpleQueue.
 *
 * @param <E> type.
 */
public class SimpleQueue<E> {
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
     * Геттер size.
     *
     * @return size.
     */
    public int size() {
        return this.list.getSize();
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
            list.removeFirst();
            if (list.getSize() == 0) {
                list = new SimpleLinkedList<E>();

            }
        } else {
            list = new SimpleLinkedList<E>();
        }
        return (E) a;
    }

}
