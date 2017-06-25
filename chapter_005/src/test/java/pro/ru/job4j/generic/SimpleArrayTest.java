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
        SimpleArray<User> simple = new SimpleArray<>(2);
        simple.add(new User("a"));
        simple.add(new User("l"));
        Object[] result = simple.getObjects();
        Object[] expected = new Object[]{new User("a"), new User("l")};
        assertThat(result, is(expected));
    }

    /**
     * Тест геттера элемента.
     */
    @Test
    public void whenGetSecondUserThenReturnIt() {
        SimpleArray<User> simple = new SimpleArray<>(2);
        simple.add(new User("a"));
        simple.add(new User("l"));
        User result = simple.get(1);
        User expected = new User("l");
        assertThat(result, is(expected));

    }

    /**
     * Тест обновления элемента.
     */
    @Test
    public void whenUpdateSecondStringThenReplaceIt() {
        SimpleArray<User> simple = new SimpleArray<>(3);
        simple.add(new User("a"));
        simple.add(new User("l"));
        simple.add(new User("j"));
        simple.update(new User("l"));
        Object[] result = simple.getObjects();
        Object[] expected = new Object[]{new User("a"), new User("l"), new User("j")};
        assertThat(result, is(expected));

    }

    /**
     * Тест удаления.
     */
    @Test
    public void whenDeleteStringThenArrayHasNotThisString() {
        SimpleArray<User> simple = new SimpleArray<>(3);
        simple.add(new User("a"));
        simple.add(new User("l"));
        simple.add(new User("j"));
        simple.delete(new User("l"));
        Object[] result = simple.getObjects();
        Object[] expected = new Object[]{new User("a"), new User("j")};
        assertThat(result, is(expected));

    }
}