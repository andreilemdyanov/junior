package pro.ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class IteratorEvenTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 10.06.2017
 */
public class IteratorEvenTest {
    /**
     * Тест возвращения четных чисел из массива.
     */
    @Test
    public void whenIteratorReturnOnlyEvenNumbers() {
        IteratorEven it = new IteratorEven(new int[]{1, 2, 3, 3, 3, 4, 5, 5, 6, 7});
        int[] result = new int[3];
        int pos = 0;
        while (it.hasNext()) {
            result[pos++] = (Integer) (it.next());
        }
        assertThat(result, is(new int[]{2, 4, 6}));
    }

    /**
     * Тест hasNext.
     */
    @Test
    public void whenHasNextShouldReturnFalse() {
        IteratorEven it = new IteratorEven(new int[]{1});
        while (it.hasNext()) {
            it.next();
        }
        it.hasNext();
        boolean result = it.hasNext();
        assertThat(result, is(false));
    }
}