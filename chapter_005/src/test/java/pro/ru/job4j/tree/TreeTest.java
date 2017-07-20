package pro.ru.job4j.tree;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class TreeTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 20.07.2017
 */
public class TreeTest {
    /**
     * Тест добавления родителя.
     */
    @Test
    public void whenAddParentThenTreeHasIt() {
        Tree<String> tree = new Tree<>("Dad");
        tree.addParent("Mother");
        tree.addParent("Bubba");

        int result = tree.getSize();
        int expected = 3;

        assertThat(result, is(expected));

    }

    /**
     * Тест добавления ребенка когда родитель есть.
     */
    @Test
    public void whenAddChildThenReturnTrue() {
        Tree<String> tree = new Tree<>("Dad");
        tree.addParent("Mother");
        tree.addParent("Bubba");

        boolean result = tree.add("Mother", "Baby");

        assertThat(result, is(true));
    }

    /**
     * Тест добавления ребенка когда родителя нет.
     */
    @Test
    public void whenAddChildThenReturnFalse() {
        Tree<String> tree = new Tree<>("Dad");
        tree.addParent("Mother");
        tree.addParent("Bubba");

        boolean result = tree.add("M", "Baby");

        assertThat(result, is(false));
    }

    /**
     * Тест итератора.
     */
    @Test
    public void whenIteratorNextReturnObject() {
        Tree<String> tree = new Tree<>("Dad");
        tree.addParent("Mother");
        tree.addParent("Bubba");

        Iterator it = tree.iterator();
        String result = it.next().toString();
        String expected = "Node{value=Bubba}";

        assertThat(result, is(expected));
    }

    /**
     * Тест итератора, элементы еще есть.
     */
    @Test
    public void whenIteratorHasNextReturnTrue() {
        Tree<String> tree = new Tree<>("Dad");
        tree.addParent("Mother");
        tree.addParent("Bubba");

        Iterator it = tree.iterator();
        boolean result = it.hasNext();

        assertThat(result, is(true));

    }

    /**
     * Тест итератора, элементов больше нет.
     */
    @Test
    public void whenIteratorHasNextReturnFalse() {
        Tree<String> tree = new Tree<>("Dad");
        tree.addParent("Mother");
        tree.addParent("Bubba");

        Iterator it = tree.iterator();
        while (it.hasNext()) {
            it.next();
        }
        boolean result = it.hasNext();

        assertThat(result, is(false));
    }
}