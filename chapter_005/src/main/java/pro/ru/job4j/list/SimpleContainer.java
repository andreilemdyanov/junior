package pro.ru.job4j.list;

/**
 * Interface SimpleContainer.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 22.06.2017
 */
public interface SimpleContainer<E> extends Iterable<E> {
    void add(E e);
    E get(int index);
}
