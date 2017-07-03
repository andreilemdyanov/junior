package pro.ru.job4j.set;

import org.junit.Test;
import pro.ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class SimpleLinkedSetTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 03.07.2017
 */
public class SimpleLinkedSetTest {
    /**
     * Тест добавления дубликатов.
     */
    @Test
    public void whenNextElementNotSame() {
        SimpleLinkedSet<Integer> set = new SimpleLinkedSet<>();
        set.add(1);
        set.add(1);
        set.add(1);
        set.add(23);
        Iterator it = set.iterator();
        it.next();
        Object result = it.next();
        assertThat(result, is(23));

    }

    /**
     * Тест размера.
     */
    @Test
    public void whenGetSizeSetThenReturnCorrect() {
        SimpleLinkedSet<Integer> set = new SimpleLinkedSet<>();
        set.add(1);
        set.add(1);
        set.add(1);
        set.add(23);
        set.add(23);
        set.add(23);
        Integer result = set.getSize();
        assertThat(result, is(2));

    }

    /**
     * Тест заполнения.
     */
    @Test
    public void whenIteratorReturnAllElements() {
        SimpleLinkedSet<Integer> set = new SimpleLinkedSet<>();
        set.add(1);
        set.add(1);
        set.add(1);
        set.add(23);
        set.add(23);
        set.add(23);
        set.add(2);
        set.add(55);
        SimpleArrayList<Integer> result = new SimpleArrayList<>();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            result.add((Integer) it.next());
        }
        SimpleArrayList<Integer> expected = new SimpleArrayList<>();
        expected.add(1);
        expected.add(23);
        expected.add(2);
        expected.add(55);
        assertThat(result, is(expected));

    }
}