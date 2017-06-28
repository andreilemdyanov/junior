package pro.ru.job4j.generic;

/**
 * Class UserStore.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 22.06.2017
 */

/**
 * UserStore.
 *
 * @param <T> type.
 */
public class UserStore<T extends User> extends BaseStore<User> {

    /**
     * Конструктор.
     *
     * @param u контейнер.
     */
    public UserStore(SimpleArray<User> u) {
        super(u);
    }

    /**
     * Переопределение toString.
     *
     * @return строка.
     */
    @Override
    public String toString() {
        return "UserStore{"
                + "r= "
                + this.forEach()
                + '}';
    }
}
