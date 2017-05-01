package ru.job4j.task5;

import ru.job4j.task4.Tracker;
import ru.job4j.task4.Task;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
*Class StubInputTest.
*
*@author Andrey Lemdyanov
*@since 29.04.2017
*/
public class StubInputTest {
	/**
	*Метод тестирует добавление заявки.
	*/
	@Test
public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
 Tracker tracker = new Tracker();     // создаём Tracker
 Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
 new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
 assertThat(tracker.getAll()[0].getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
}
/**
*Метод тестирует получение списка заявок.
*/
@Test
public void whenUserAddTwoItemsThenTrackerHasShowItems() {
 Tracker tracker = new Tracker();
 Input input = new StubInput(new String[]{"0", "test name", "desc", "0", "test name next", "description", "1", "6"});
 new StartUI(input, tracker).init();
 assertThat(tracker.findAll()[0].getName() + tracker.findAll()[1].getName() + " ", is("test name" + "test name next" + " "));
}
/**
*Метод тестирует редактирование заявки.
*/
@Test
public void whenUserAddTwoItemsAndEditOneThenTrackerHasTwoItems() {
 Tracker tracker = new Tracker();
 Task task = new Task("test name", "desc", 123L);
 tracker.add(task);
 Input input = new StubInput(new String[]{"0", "test name next", "description", "2", tracker.getAll()[0].getId(), "some", "words", "6"});
 new StartUI(input, tracker).init();
 assertThat(tracker.findAll()[0].getName() + tracker.findAll()[1].getName() + " ", is("some" + "test name next" + " "));
}
/**
*Метод тестирует удаление заявки.
*/
@Test
public void whenUserAddThreeItemsDeleteOneThenTrackerHasShowTwoItems() {
 Tracker tracker = new Tracker();
 Task task = new Task("test name", "desc", 123L);
 Task tasknew = new Task("some", "words", 133L);
 tracker.add(task);
 tracker.add(tasknew);
 Input input = new StubInput(new String[]{"0", "test name next", "description", "0", "test name third", "another desc", "3", tracker.getAll()[0].getId(), "1", "6"});
 new StartUI(input, tracker).init();
 assertThat(tracker.findAll()[0].getName() + tracker.findAll()[1].getName() + " ", is("some" + "test name next" + " "));
}
/**
*Метод тестирует поиск по id.
*/
@Test
public void whenUserAddTwoItemsSearchIdThenTrackerHasShowOneItem() {
 Tracker tracker = new Tracker();
 Task task = new Task("test name", "desc", 123L);
 tracker.add(task);
 Input input = new StubInput(new String[]{"0", "test name third", "another desc", "4", tracker.getAll()[0].getId(), "6"});
 new StartUI(input, tracker).init();
 assertThat(tracker.findById(tracker.getAll()[0].getId()), is(task));
}
/**
*Метод тестирует поиск по имени.
*/
@Test
public void whenUserAddThreeItemsSearchNameThenTrackerHasShowArrayItems() {
 Tracker tracker = new Tracker();
 Task task = new Task("test name", "desc", 123L);
 Task tasknew = new Task("test name", "another", 163L);
 tracker.add(task);
 tracker.add(tasknew);
 Input input = new StubInput(new String[]{"0", "test name third", "another desc", "5", tracker.getAll()[0].getName(), "6"});
 new StartUI(input, tracker).init();
 assertThat(tracker.findByName("test name")[0].getName() + tracker.findByName("test name")[1].getName() + " ", is("test name" + "test name" + " "));
}

}