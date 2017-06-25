package pro.ru.job4j.generic;

/**
 * Class User.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 22.06.2017
 */
public class User extends Base {
    /**
     * Конструктор.
     *
     * @param id ай ди.
     */
    public User(String id) {
        this.setId(id);
    }

    /**
     * Переопределение equals.
     *
     * @param obj объект.
     * @return равны?
     */
    @Override
    public boolean equals(Object obj) {
        String o = ((User) obj).getId();
        return this.getId().equals(o);
    }
}
