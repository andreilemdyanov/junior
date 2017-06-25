package pro.ru.job4j.generic;

/**
 * Class RoleStore.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 22.06.2017
 */
public class RoleStore<T extends Role> extends BaseStore<Role> {

    /**
     * Конструктор.
     *
     * @param r контейнер.
     */
    public RoleStore(SimpleArray<Role> r) {
        super(r);
    }

    /**
     * Переопределение toString.
     *
     * @return строка.
     */
    @Override
    public String toString() {
        return "RoleStore{"
                + "r= "
                + this.forEach()
                + '}';
    }
}
