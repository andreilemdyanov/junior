package pro.ru.job4j.iterator;

import java.util.Iterator;

/**
 * Interface IteratorIter.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 0.1
 */

/**
 * IteratorIter.
 *
 * @param <Integer> type.
 */
public interface IteratorIter<Integer> extends Iterator {
    /**
     * Конверт.
     *
     * @param it итератор итераторов.
     * @return итератор.
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it);
}
