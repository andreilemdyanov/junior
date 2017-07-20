package pro.ru.job4j.tree;

/**
 * Interface SimpleTree.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 14.07.2017
 */

/**
 * SimpleTree.
 * @param <E> type.
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return добавлен?
     */
    boolean add(E parent, E child);

}
