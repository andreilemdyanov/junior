package ru.job4j.task4;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class TrackerTest.
 *
 * @author Andrey Lemdyanov
 * @since 24.04.2017
 */
public class TrackerTest {
    /**
     * Метод для проверки добавления элемента.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.getAll()[0], is(item));
    }

    /**
     * Метод для проверки замены элемента.
     */
    @Test
    public void whenAddNewItemAndReplaceItThenTrackerHasReplaceItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        Item replace = new Item("test2", "replacedItem", 248L);
        tracker.add(item);
        replace.setId(item.getId());
        tracker.update(replace);
        assertThat(tracker.getAll()[0], is(replace));
    }

    /**
     * Метод для проверки удаления элемента.
     */
    @Test
    public void whenAddTwoNewItemsAndDeleteOneThenTrackerHasOneItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        Item item2 = new Item("test2", "testDelete", 222L);
        Item item3 = new Item("test3", "test", 255L);
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        tracker.delete(item2);
        Item[] result = tracker.findAll();
        Item[] expectArray = new Item[]{item, item3};
        assertThat(result, is(expectArray));
    }

    /**
     * Метод для проверки получения списка всех заявок.
     */
    @Test
    public void whenAddNullItemThenReturnNotNullArray() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        Item item2 = new Item("test2", "testDelete", 222L);
        Item item3 = new Item("test1", "testDes", 125L);
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        Item[] result = tracker.findAll();
        Item[] expect = new Item[]{item, item2, item3};
        assertThat(result, is(expect));
    }

    /**
     * Метод для проверки поиска элементов по имени.
     */
    @Test
    public void whenAddThreeNewItemsAndFindNameThenReturnArrayTwo() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        Item item2 = new Item("test2", "testDelete", 222L);
        Item item3 = new Item("test1", "testDes", 125L);
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        Item[] result = tracker.findByName("test1");
        Item[] expect = new Item[]{tracker.getAll()[0], tracker.getAll()[2]};
        assertThat(result, is(expect));
    }

    /**
     * Метод для проверки поиска по id.
     */
    @Test
    public void whenAddTwoItemsFindIdThenReturnItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        Item item2 = new Item("test2", "testDelete", 222L);
        Item item3 = new Item("test3", "testDes", 125L);
        tracker.add(item);
        tracker.add(item2);
        item3.setId(item.getId());
        Item result = tracker.findById(item3.getId());
        assertThat(result, is(item));
    }
}