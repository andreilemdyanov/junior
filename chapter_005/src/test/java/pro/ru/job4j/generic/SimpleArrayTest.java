package pro.ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Class SimpleArrayTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 17.06.2017
 */
public class SimpleArrayTest {
    /**
     * Тест добавления.
     */
    @Test
    public void whenAddTwoStringThenArrayHasIt() {
        SimpleArray<String> simple = new SimpleArray<>(2);
        simple.add("a");
        simple.add("l");
        Object[] result = simple.getObjects();
        Object[] expected = new Object[]{"a", "l"};
        assertThat(result, is(expected));
    }

    /**
     * Тест геттера элемента.
     */
    @Test
    public void whenGetSecondStringThenReturnIt() {
        SimpleArray<String> simple = new SimpleArray<>(2);
        simple.add("a");
        simple.add("l");
        String result = simple.get(1);
        String expected = "l";
        assertThat(result, is(expected));

    }

    /**
     * Тест обновления элемента.
     */
    @Test
    public void whenUpdateSecondStringThenReplaceIt() {
        SimpleArray<String> simple = new SimpleArray<>(3);
        simple.add("a");
        simple.add("l");
        simple.add("j");
        simple.update(1, "p");
        Object[] result = simple.getObjects();
        Object[] expected = new Object[]{"a", "p", "j"};
        assertThat(result, is(expected));

    }

    /**
     * Тест удаления.
     */
    @Test
    public void whenDeleteStringThenArrayHasNotThisString() {
        SimpleArray<String> simple = new SimpleArray<>(3);
        simple.add("a");
        simple.add("l");
        simple.add("j");
        simple.delete("l");
        Object[] result = simple.getObjects();
        Object[] expected = new Object[]{"a", "j"};
        assertThat(result, is(expected));

    }
}