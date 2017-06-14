package ru.job4j.additional;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class ChangeCoinsTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 06.06.2017
 */
public class ChangeCoinsTest {
    /**
     * Тест простого размена.
     */
    @Test
    public void whenYourCashSimpleChange() {
        ChangeCoins simple = new ChangeCoins(109);
        List<Integer> result = simple.changeSimple();
        List<Integer> expected = new ArrayList<>(Arrays.asList(10, 1, 4));
        assertThat(result, is(expected));
    }

    /**
     * Тест всех разменов.
     */
    @Test
    public void whenYourCashComplexChange() {
        ChangeCoins complex = new ChangeCoins(100);
        List<Integer> result = complex.changeComplex().get(8);
        List<Integer> expected = new ArrayList<>(Arrays.asList(0, 8, 60));
        assertThat(result, is(expected));
    }
}