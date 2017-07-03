package pro.ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class NodeTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 03.07.2017
 */
public class NodeTest {
    /**
     * Тест первого нода.
     */
    @Test
    public void whenFirstNodeHasCycle() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(first);
        boolean result = first.hasCycle();
        assertThat(result, is(true));
    }

    /**
     * Тест второго нода.
     */
    @Test
    public void whenSecondNodeHasCycle() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(first);
        boolean result = two.hasCycle();
        assertThat(result, is(true));
    }

    /**
     * Тест третьего нода.
     */
    @Test
    public void whenThirdNodeHasCycle() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(first);
        boolean result = third.hasCycle();
        assertThat(result, is(true));
    }

    /**
     * Тест четвертого нода.
     */
    @Test
    public void whenFourNodeHasCycle() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> four = new Node<>(4);

        first.setNext(two);
        two.setNext(third);
        third.setNext(four);
        four.setNext(first);
        boolean result = four.hasCycle();
        assertThat(result, is(true));
    }

}