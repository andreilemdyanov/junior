package pro.ru.job4j.iterator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class IteratorSimpleTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 10.06.2017
 */
public class IteratorSimpleTest {
    /**
     * Тест который возвращает массив простых чисел.
     */
    @Test
    public void whenReturnOnlySimpleNumbersFromArray() {

        IteratorSimple it = new IteratorSimple(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17});
        int[] result = new int[8];
        int pos = 0;
        while (it.hasNext()) {
            result[pos++] = (Integer) it.next();
        }
        assertThat(result, is(new int[]{1, 2, 3, 5, 7, 11, 13, 17}));
    }
}