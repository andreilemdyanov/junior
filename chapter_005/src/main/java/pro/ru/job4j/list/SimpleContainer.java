package pro.ru.job4j.list;

/**
 * Interface SimpleContainer.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 22.06.2017
 */

/**
 * SimpleContainer.
 *
 * @param <E> type.
 */
public interface SimpleContainer<E> extends Iterable<E> {
    /**
     * Добавить.
     *
     * @param e элемент.
     */
    void add(E e);

    /**
     * Геттер.
     *
     * @param index индекс.
     * @return элемент.
     */
    E get(int index);
}
