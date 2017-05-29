package ru.job4j.bank;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Class BankMapTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 29.05.2017
 */
public class BankMapTest {
    /**
     * Тест добавления пользователя.
     */
    @Test
    public void whenAddUserThenMapHasUser() {
        Map<User, List<Account>> map = new HashMap<>();
        BankMap b = new BankMap(map);
        b.addUser(new User("Pavel", "fjfjg23"));
        Map<User, List<Account>> expected = new HashMap<>();
        expected.put(new User("Pavel", "fjfjg23"), new ArrayList<>());
        assertThat(b.getMap(), is(expected));
    }

    /**
     * Тест удаления пользователя.
     */
    @Test
    public void whenDeleteUserThenMapHasNotUser() {
        Map<User, List<Account>> map = new HashMap<>();
        BankMap b = new BankMap(map);
        b.addUser(new User("Pavel", "fjfjg23"));
        b.addUser(new User("Maks", "parampam"));
        b.deleteUser(new User("Maks", "parampam"));
        Map<User, List<Account>> expected = new HashMap<>();
        expected.put(new User("Pavel", "fjfjg23"), new ArrayList<>());
        assertThat(b.getMap(), is(expected));
    }

    /**
     * Тест добавления аккаунта пользователю.
     */
    @Test
    public void whenAddAccountToUserThenUserHasAccount() {
        Map<User, List<Account>> map = new HashMap<>();
        BankMap b = new BankMap(map);
        b.addUser(new User("Pavel", "fjfjg23"));
        b.addAccountToUser(new User("Pavel", "fjfjg23"),
                new Account(2350, "rtt"));
        Map<User, List<Account>> expected = new HashMap<>();
        List<Account> acc = new ArrayList<>();
        acc.add(new Account(2350, "rtt"));
        expected.put(new User("Pavel", "fjfjg23"), acc);
        assertThat(b.getMap(), is(expected));
    }

    /**
     * Тест удаления аккаунта пользователя.
     */
    @Test
    public void whenDeleteAccountFromUserThenUserHasNotAccount() {
        Map<User, List<Account>> map = new HashMap<>();
        BankMap b = new BankMap(map);
        b.addUser(new User("Pavel", "fjfjg23"));
        b.addAccountToUser(new User("Pavel", "fjfjg23"),
                new Account(2350, "rtt"));
        b.addAccountToUser(new User("Pavel", "fjfjg23"),
                new Account(45650, "another"));
        b.deleteAccountFromUser(new User("Pavel", "fjfjg23"),
                new Account(2350, "rtt"));
        Map<User, List<Account>> expected = new HashMap<>();
        List<Account> acc = new ArrayList<>();
        acc.add(new Account(45650, "another"));
        expected.put(new User("Pavel", "fjfjg23"), acc);
        assertThat(b.getMap(), is(expected));
    }

    /**
     * Тест создания списка аккаунтов пользователя.
     */
    @Test
    public void whenGetUserAccountsThenShowAllAccounts() {
        Map<User, List<Account>> map = new HashMap<>();
        BankMap b = new BankMap(map);
        b.addUser(new User("Pavel", "fjfjg23"));
        b.addAccountToUser(new User("Pavel", "fjfjg23"),
                new Account(2350, "rtt"));
        b.addAccountToUser(new User("Pavel", "fjfjg23"),
                new Account(45650, "another"));
        List<Account> expected = new ArrayList<>();
        expected.add(new Account(2350, "rtt"));
        expected.add(new Account(45650, "another"));
        assertThat(b.getUserAccounts(new User("Pavel", "fjfjg23")), is(expected));
    }

    /**
     * Тест перевода средств.
     */
    @Test
    public void whenTransferMoneyThenTrue() {
        Map<User, List<Account>> map = new HashMap<>();
        BankMap b = new BankMap(map);
        b.addUser(new User("Pavel", "fjfjg23"));
        b.addAccountToUser(new User("Pavel", "fjfjg23"),
                new Account(2350, "rtt"));
        b.addUser(new User("Maks", "parampam"));
        b.addAccountToUser(new User("Maks", "parampam"),
                new Account(45650, "another"));
        boolean expected = b.transferMoney(new User("Pavel", "fjfjg23"),
                new Account(2350, "rtt"),
                new User("Maks", "parampam"),
                new Account(45650, "another"),
                350);
        assertTrue(expected);

    }

}