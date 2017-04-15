package ru.job4j.inspection;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Check Test.
*
* @author Andrey Lemdyanov (mailto:lemdyanov5@mail.ru)
* @version $Id$
* @since 0.1
*/
public class CheckTest {
/**
*Метод для проверки условия когда sub является подстрокой origin.
*/
	@Test
	public void whenSubIsOriginSubstring() {
		Check check = new Check();
		boolean result = check.contains("обладатель", "ель");
		boolean expect = true;
		assertThat(result, is(expect));
	}
/**
*Метод для проверки условия когда sub не является подстрокой origin.
*/
	@Test
		public void whenSubIsNotOriginSubstring() {
		Check check = new Check();
		boolean result = check.contains("обладатель", "кот");
		boolean expect = false;
		assertThat(result, is(expect));
	}
}