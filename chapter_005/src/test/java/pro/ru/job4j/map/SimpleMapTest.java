package pro.ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Class SimpleMapTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 13.07.2017
 */
public class SimpleMapTest {
    /**
     * Тест метода get.
     */
    @Test
    public void whenGetThenReturnValue() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.insert("1", "R");
        map.insert("fg", "R");
        map.insert("Fghy", "R");
        String result = map.get("1");
        String expected = "R";
        assertThat(result, is(expected));

    }

    /**
     * Тест вставки.
     */
    @Test
    public void whenInsertObjectThenContainerHasIt() {
        SimpleMap<String, String> map = new SimpleMap<>();
        Boolean result = map.insert("1", "R");
        assertThat(result, is(true));

    }

    /**
     * Тест метода loadF.
     */
    @Test
    public void whenLoadFWorksThenContainerBecameBigger() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.insert("1", "R");
        map.insert("fg", "R");
        map.insert("Fghy", "R");
        map.insert("fffr", "R");
        map.insert("3r3r", "Y");
        map.insert("kdgudfhgkldf", "Rad");
        map.insert("ffgdgfr", "R");
        map.insert("kgottg", "Rxv");
        map.insert("fffrOE", "R");
        map.insert("339495", "R");
        int result = map.getContainer().length;
        int expected = 20;
        assertThat(result, is(expected));

    }

    /**
     * Тест удаления.
     */
    @Test
    public void whenInsertObjectAndDeleteThenContainerHasNotIt() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.insert("1", "R");
        map.delete("1");
        Object[] result = map.getContainer();
        Object expected = new Object[10];
        assertThat(result, is(expected));

    }

    /**
     * Тест итератора.
     */
    @Test
    public void whenIteratorHasNextReturnFalse() {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.insert("1", "R");
        map.insert("fg", "R");
        map.insert("Fghy", "R");
        map.insert("fffr", "R");
        map.insert("3r3r", "Y");
        map.insert("kdgudfhgkldf", "Rad");
        map.insert("ffgdgfr", "R");
        Iterator it = map.iterator();
        while (it.hasNext()) {
            it.next();
        }
        Boolean result = it.hasNext();
        assertThat(result, is(false));
    }
}