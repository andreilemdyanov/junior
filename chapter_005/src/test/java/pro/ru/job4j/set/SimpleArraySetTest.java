package pro.ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class SimpleArraySetTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 01.07.2017
 */
public class SimpleArraySetTest {
    /**
     * Тест добавления элементов.
     */
    @Test
    public void whenAddThreeIntegersThenSetHasIt() {
        SimpleArraySet<Integer> set = new SimpleArraySet<>();
        set.add(1);
        set.add(2);
        set.add(2);
        set.add(3);
        set.add(1);
        set.add(1);
        Object[] result = set.getContainer();
        Object[] expected = new Object[]{1, 2, 3, null, null, null, null, null, null, null};
        assertThat(result, is(expected));
    }

    /**
     * Тест итератора.
     */
    @Test
    public void whenIteratorReturnSecondValue() {
        SimpleArraySet<Integer> set = new SimpleArraySet<>();
        set.add(1);
        set.add(2);
        set.add(2);
        set.add(3);
        set.add(1);
        set.add(1);
        Iterator it = set.iterator();
        it.next();
        Object result = it.next();
        Object expected = 2;
        assertThat(result, is(expected));
    }
}