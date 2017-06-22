package pro.ru.job4j.list;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class SimpleArrayListTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 22.06.2017
 */
public class SimpleArrayListTest {
    /**
     * Тест добавления.
     */
    @Test
    public void whenAddTwelveObjectsThenContainerHasIt() {
        SimpleArrayList<Integer> simple = new SimpleArrayList<>();
        simple.add(1);
        simple.add(2);
        simple.add(3);
        simple.add(4);
        simple.add(5);
        simple.add(6);
        simple.add(7);
        simple.add(8);
        simple.add(9);
        simple.add(10);
        simple.add(11);
        simple.add(12);
        Object[] result = simple.getContainer();
        Object[] expected = new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, null, null, null, null, null, null, null, null};
        assertThat(result, is(expected));
    }

    /**
     * Тест геттера.
     */
    @Test
    public void whenGetObjectThenReturnIt() {
        SimpleArrayList<String> simple = new SimpleArrayList<>();
        simple.add("1");
        simple.add("2");
        simple.add("3");
        String result = simple.get(1);
        String expected = "2";
        assertThat(result, is(expected));

    }

    /**
     * Тест итератора.
     */
    @Test
    public void whenHasNextThenFalse() {
        SimpleArrayList<Integer> simple = new SimpleArrayList<>();
        simple.add(1);
        simple.add(2);
        simple.add(3);
        Iterator it = simple.iterator();
        while (it.hasNext()) {
            it.next();
        }
        assertThat(it.hasNext(), is(false));

    }
}