package pro.ru.job4j.generic;

/**
 * Class Role.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 22.06.2017
 */
public class Role extends Base {
    /**
     * Конструктор.
     *
     * @param id ай ди.
     */
    public Role(String id) {
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
        String o = ((Role) obj).getId();
        return this.getId().equals(o);
    }
}
