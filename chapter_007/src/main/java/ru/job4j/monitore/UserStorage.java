package ru.job4j.monitore;

import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class UserStorage.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 31.08.2017
 */
@ThreadSafe
public class UserStorage {
    /**
     * Потокобезопасный HashMap.
     */
    private Map<Integer, User> map = new ConcurrentHashMap<>();

    /**
     * Геттер пользователя.
     *
     * @param id id.
     * @return пользователь.
     */
    User get(int id) {
        return map.get(id);
    }

    /**
     * Добавление пользователя.
     *
     * @param user пользователь.
     */
    void add(User user) {
        map.put(user.getId(), user);
    }

    /**
     * Обновление пользователя.
     *
     * @param user пользователь.
     */
    void update(User user) {
        this.add(user);
    }

    /**
     * Удаление пользователя.
     *
     * @param id пользователь.
     */
    void delete(int id) {
        map.remove(id);
    }

    /**
     * Перевод со счета на счет.
     *
     * @param fromId от кого.
     * @param toId   кому.
     * @param amount сумма.
     */
    void transfer(int fromId, int toId, int amount) {
        map.get(fromId).setAmount(map.get(fromId).getAmount() - amount);
        map.get(toId).setAmount(map.get(toId).getAmount() + amount);
    }

    /**
     * Переопределение toString().
     *
     * @return строка.
     */
    @Override
    public String toString() {
        return "UserStorage{"
                + "map="
                + map
                + '}';
    }
}
