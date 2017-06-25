package pro.ru.job4j.list;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class SimpleStackTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 25.06.2017
 */
public class SimpleStackTest {
    /**
     * Тест добавления строки.
     */
    @Test
    public void whenPushStringInStackThenReturnIt() {
        SimpleStack<String> stack = new SimpleStack<>();
        String result = stack.push("1");
        String expected = "1";
        assertThat(result, is(expected));
    }

    /**
     * Тест возвращения строки.
     */
    @Test
    public void whenPopStringInStackThenReturnIt() {
        SimpleStack<String> stack = new SimpleStack<>();
        stack.push("1");
        String result = stack.pop();
        String expected = "1";
        assertThat(result, is(expected));
    }

    /**
     * Тест когда pop() уже вернул все элементы.
     */
    @Test
    public void whenPopStringInStackThenReturnNull() {
        SimpleStack<String> stack = new SimpleStack<>();
        stack.push("1");
        stack.pop();
        String result = stack.pop();
        String expected = null;
        assertThat(result, is(expected));
    }

    /**
     * Тест итератора, когда элементов больше нет.
     */
    @Test
    public void whenHasNextThenFalse() {
        SimpleStack<Integer> list = new SimpleStack<>();
        list.push(1);
        list.push(2);
        list.push(3);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            it.next();
        }
        assertThat(it.hasNext(), is(false));
    }

    /**
     * Тест итератора, проверяем второй элемент.
     */
    @Test
    public void whenHasNextThenReturnSecondInteger() {
        SimpleStack<Integer> list = new SimpleStack<>();
        list.push(1);
        list.push(2);
        list.push(3);
        Iterator it = list.iterator();
        it.next();
        Integer result = (Integer) it.next();
        Integer expected = 2;
        assertThat(result, is(expected));
    }
}