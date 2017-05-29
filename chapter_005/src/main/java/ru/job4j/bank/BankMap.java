package ru.job4j.bank;

import java.util.*;

/**
 * Class BankMap.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 29.05.2017
 */
public class BankMap {
    /**
     * Поле для map.
     */
    Map<User, List<Account>> map;

    /**
     * Конструктор.
     *
     * @param map инициализация.
     */
    public BankMap(Map<User, List<Account>> map) {
        this.map = map;
    }

    /**
     * Добавление пользователя.
     *
     * @param user пользователь.
     */
    public void addUser(User user) {
        List<Account> list = new ArrayList<>();
        this.map.put(user, list);
    }

    /**
     * Удаление пользователя.
     *
     * @param user пользователь.
     */
    public void deleteUser(User user) {
        Iterator<User> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            User each = iter.next();
            if (each.equals(user)) {
                iter.remove();
            }
        }
    }

    /**
     * Добавление аккаунта пользователю.
     *
     * @param user    пользователь.
     * @param account аккаунт.
     */
    public void addAccountToUser(User user, Account account) {
        for (Map.Entry<User, List<Account>> entry : map.entrySet()) {
            if (entry.getKey().equals(user)) {
                entry.getValue().add(account);
            }
        }
    }

    /**
     * Удаление аккаунта пользователя.
     *
     * @param user    пользователь.
     * @param account аккаунт.
     */
    public void deleteAccountFromUser(User user, Account account) {
        for (Map.Entry<User, List<Account>> entry : map.entrySet()) {
            if (entry.getKey().equals(user)) {
                for (Account each : entry.getValue()) {
                    if (each.getRequisites().equals(account.getRequisites())) {
                        entry.getValue().remove(account);
                    }
                }
            }
        }
    }

    /**
     * Получение списка счетов пользователя.
     *
     * @param user пользователь.
     * @return список.
     */
    public List<Account> getUserAccounts(User user) {
        List<Account> result = new ArrayList<>();
        for (Map.Entry<User, List<Account>> entry : map.entrySet()) {
            if (entry.getKey().equals(user)) {
                result.addAll(entry.getValue());
            }
        }
        return result;
    }

    /**
     * Перевод денег с одного счета на другой.
     *
     * @param srcUser    пользователь отправитель.
     * @param srcAccount аккаунт с которого переводят.
     * @param dstUser    пользователь получатель.
     * @param dstAccount аккаунт на который переводят.
     * @param amount     сумма перевода.
     * @return успешна ли операция?
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean result = true;
        if (!this.map.containsKey(srcUser)) {
            result = false;
        }
        for (Map.Entry<User, List<Account>> entry : map.entrySet()) {
            if (entry.getKey().equals(srcUser)) {
                for (Account account : entry.getValue()) {
                    if (account.getRequisites().equals(srcAccount.getRequisites())) {
                        if ((account.getValue() - amount) < 0) {
                            result = false;
                        }
                        account.setValue(account.getValue() - amount);
                    }
                }
            }
        }
        for (Map.Entry<User, List<Account>> entry : map.entrySet()) {
            if (entry.getKey().equals(dstUser)) {
                for (Account account : entry.getValue()) {
                    if (account.getRequisites().equals(dstAccount.getRequisites())) {
                        account.setValue(account.getValue() + amount);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Геттер для map.
     *
     * @return map.
     */
    public Map<User, List<Account>> getMap() {
        return map;
    }

}
