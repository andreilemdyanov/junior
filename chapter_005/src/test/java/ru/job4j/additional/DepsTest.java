package ru.job4j.additional;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class DepsTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 22.06.2017
 */
public class DepsTest {
    /**
     * Тест добавления департаментов.
     */
    @Test
    public void whenAddToListDepsThenListHasIt() {
        Deps d = new Deps(new ArrayList<>(Arrays.asList("K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2")));
        d.addFirst();
        List<String> result = d.getDeps();
        List<String> expected = new ArrayList<>(Arrays.asList("K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2", "K1", "K2\\SK1"));
        assertThat(result, is(expected));
    }

    /**
     * Тест сортировки по убыванию с учетом длины.
     */
    @Test
    public void whenListSortThenOrderRight() {
        Deps d = new Deps(new ArrayList<>(Arrays.asList("K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2", "K1", "K2\\SK1")));
        List<String> result = d.sortAll();
        List<String> expected = new ArrayList<>(Arrays.asList("K2", "K2\\SK1", "K2\\SK1\\SSK2",
                "K2\\SK1\\SSK1", "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"));
        assertThat(result, is(expected));
    }

}