package pro.ru.job4j.iterator;

import org.junit.Test;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class IteratorArrayTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 03.06.2017
 */
public class IteratorArrayTest {
    /**
     * Тест возвращения всех чисел из двухмерного массива.
     */
    @Test
    public void whenTwoDimensionArrayIterateThenReturnAllNumbers() {
        IteratorArray it = new IteratorArray(new int[][]{{1, 2}, {3, 4}, {5, 6, 7}});
        String result = " ";
        while (it.hasNext()) {
            result += (Integer) it.next();
        }
        String expected = " 1234567";
        assertThat(result, is(expected));
    }

    /**
     * Тест hasNext.
     */
    @Test
    public void whenHasNextShouldReturnFalse() {
        IteratorArray it = new IteratorArray(new int[][]{{1, 2}, {3}});
        while (it.hasNext()) {
            it.next();
        }
        boolean result = it.hasNext();
        assertThat(result, is(false));

    }
}