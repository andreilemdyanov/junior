package ru.job4j.jdbc.tracker;

import org.junit.Test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class TrackerTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 27.12.2017
 */
public class TrackerTest {

    private DBConnection db;

    public TrackerTest() {
        this.db = new DBConnection();
        db.createTable();
    }

    /**
     * Метод удаляет таблицу и закрывает соединение.
     */
    private void drop() {
        String sql = "DROP TABLE item";
        Statement st;
        try {
            st = db.getConn().createStatement();
            st.executeUpdate(sql);
            if (db != null) {
                db.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Тест метода add.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker(db);
        Item item = new Item("Mike", "bla bla bla");
        tracker.add(item);
        Item item2 = new Item(item.getId(), "Mike", "bla bla bla");
        item2.setCreate(item.getCreate());
        assertThat(tracker.getAll().get(0), is(item2));
        this.drop();
    }

    /**
     * Тест метода update.
     */
    @Test
    public void whenAddNewItemAndReplaceItThenTrackerHasReplaceItem() {
        Tracker tracker = new Tracker(db);
        Item item = new Item("Mike", "bla bla bla");
        tracker.add(item);
        Item item2 = new Item(1, "Joe", "some words");
        Item item3 = tracker.update(item2);
        assertThat(tracker.findById(1), is(item3));
        this.drop();
    }

    /**
     * Тест метода delete.
     */
    @Test
    public void whenAddTwoNewItemsAndDeleteOneThenTrackerHasOneItem() {
        Tracker tracker = new Tracker(db);
        Item item = new Item("Mike", "bla bla bla");
        Item item2 = new Item("Joe", "some words");
        tracker.add(item);
        Item item3 = tracker.add(item2);
        tracker.delete(1);
        ArrayList<Item> list = new ArrayList<>(Arrays.asList(item3));
        assertThat(tracker.getAll(), is(list));
        this.drop();
    }

    /**
     * Тест метода findByName.
     */
    @Test
    public void whenAddThreeNewItemsAndFindNameThenReturnArrayTwo() {
        Tracker tracker = new Tracker(db);
        Item item = new Item("Mike", "bla bla bla");
        Item item2 = new Item("Joe", "some words");
        Item item3 = new Item("Mike", "anything");
        Item temp = tracker.add(item);
        tracker.add(item2);
        Item temp2 = tracker.add(item3);
        ArrayList<Item> list = new ArrayList<>(Arrays.asList(temp, temp2));
        assertThat(tracker.findByName("Mike"), is(list));
        this.drop();
    }

    /**
     * Тест метода findById.
     */
    @Test
    public void whenAddTwoItemsFindIdThenReturnItem() {
        Tracker tracker = new Tracker(db);
        Item item = new Item("Mike", "bla bla bla");
        Item item2 = new Item("Joe", "some words");
        Item temp = tracker.add(item);
        tracker.add(item2);
        assertThat(tracker.findById(1), is(temp));
        this.drop();
    }

    /**
     * Тест метода getAll.
     */
    @Test
    public void whenAddThreeItemsThenTrackerHasIt() {
        Tracker tracker = new Tracker(db);
        Item item = new Item("Mike", "bla bla bla");
        Item item2 = new Item("Joe", "some words");
        Item item3 = new Item("Bill", "anything");
        Item temp = tracker.add(item);
        Item temp2 = tracker.add(item2);
        Item temp3 = tracker.add(item3);
        ArrayList<Item> list = new ArrayList<>(Arrays.asList(temp, temp2, temp3));
        assertThat(tracker.getAll(), is(list));
        this.drop();
    }
}