package pro.ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class RoleStoreTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 22.06.2017
 */
public class RoleStoreTest {
    /**
     * Тест добавления.
     */
    @Test
    public void whenAddFiveRolesThenItWorks() {
        RoleStore role = new RoleStore(new SimpleArray<>(5));
        role.addRole(new Role("1"));
        role.addRole(new Role("2"));
        role.addRole(new Role("3"));
        role.addRole(new Role("4"));
        role.addRole(new Role("5"));
        SimpleArray result = role.getRole();
        SimpleArray<Role> expected = new SimpleArray<>(5);
        expected.add(new Role("1"));
        expected.add(new Role("2"));
        expected.add(new Role("3"));
        expected.add(new Role("4"));
        expected.add(new Role("5"));
        assertThat(result, is(expected));
    }

    /**
     * Тест обновления.
     */
    @Test
    public void whenUpdateSecondRoleThenReplacedIt() {
        RoleStore role = new RoleStore(new SimpleArray<>(5));
        role.addRole(new Role("1"));
        Role b = new Role("2");
        role.addRole(b);
        role.addRole(new Role("3"));
        role.addRole(new Role("4"));
        role.addRole(new Role("5"));
        role.updateRole(2, new Role("6"));
        SimpleArray result = role.getRole();
        SimpleArray<Role> expected = new SimpleArray<>(5);
        expected.add(new Role("1"));
        expected.add(new Role("2"));
        expected.add(new Role("6"));
        expected.add(new Role("4"));
        expected.add(new Role("5"));
        assertThat(result, is(expected));
    }

    /**
     * Тест удаления.
     */
    @Test
    public void whenDeleteFirstRoleThenItsGone() {
        RoleStore role = new RoleStore(new SimpleArray<>(5));
        role.addRole(new Role("1"));
        Role b = new Role("2");
        role.addRole(b);
        role.addRole(new Role("3"));
        role.addRole(new Role("4"));
        role.addRole(new Role("5"));
        role.deleteRole(b);
        SimpleArray result = role.getRole();
        SimpleArray<Role> expected = new SimpleArray<>(4);
        expected.add(new Role("1"));
        expected.add(new Role("3"));
        expected.add(new Role("4"));
        expected.add(new Role("5"));
        assertThat(result, is(expected));
    }
}