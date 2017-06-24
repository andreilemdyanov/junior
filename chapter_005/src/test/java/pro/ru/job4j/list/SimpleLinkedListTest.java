package pro.ru.job4j.list;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class SimpleLinkedListTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.06.2017
 */
public class SimpleLinkedListTest {
    /**
     * Тест геттера.
     */
    @Test
    public void whenGetThirdIntegerThenListReturnIt() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Object result = list.get(2);
        Object expected = 3;
        assertThat(result, is(expected));

    }

    /**
     * Тест итератора.
     */
    @Test
    public void whenHasNextThenFalse() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            it.next();
        }
        assertThat(it.hasNext(), is(false));
    }

}