package pro.ru.job4j.iterator;

import java.util.Iterator;

/**
 * Interface IteratorIter.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public interface IteratorIter<Integer> extends Iterator {
    Iterator<Integer> convert (Iterator<Iterator<Integer>> it);
}
