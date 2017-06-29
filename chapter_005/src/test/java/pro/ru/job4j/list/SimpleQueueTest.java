package pro.ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class SimpleQueueTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 29.06.2017
 */
public class SimpleQueueTest {
    /**
     * Тест добавления строки.
     */
    @Test
    public void whenPushStringInQueueThenReturnIt() {
        SimpleQueue<String> queue = new SimpleQueue<>();
        String result = queue.push("1");
        String expected = "1";
        assertThat(result, is(expected));
    }

    /**
     * Тест удаления первого элемента.
     */
    @Test
    public void whenPopStringInQueueThenReturnIt() {
        SimpleQueue<String> queue = new SimpleQueue<>();
        queue.push("1");
        queue.push("2");
        String result = queue.pop();
        String expected = "1";
        assertThat(result, is(expected));
    }

    /**
     * Тест когда pop() уже вернул все элементы.
     */
    @Test
    public void whenPopStringInQueueThenReturnNull() {
        SimpleQueue<String> queue = new SimpleQueue<>();
        queue.push("1");
        queue.pop();
        String result = queue.pop();
        String expected = null;
        assertThat(result, is(expected));
    }

}