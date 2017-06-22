package pro.ru.job4j.generic;

/**
 * Class RoleStore.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 22.06.2017
 */
public class RoleStore<T> implements Store<Role> {
    /**
     * Контейнер.
     */
    private SimpleArray<Role> r;

    /**
     * Конструктор.
     *
     * @param r контейнер.
     */
    public RoleStore(SimpleArray<Role> r) {
        this.r = r;
    }

    /**
     * Метод для отображения объекта по id.
     *
     * @return строка.
     */
    public String forEach() {
        String s = "";
        for (Object r : r.getObjects()) {
            s += "id: " + ((Role) r).getId() + " ";
        }
        return s;
    }

    /**
     * Геттер контейнера.
     *
     * @return контейнер.
     */
    public SimpleArray<Role> getRole() {
        return r;
    }

    /**
     * Добавить роль.
     *
     * @param a роль.
     */
    public void addRole(Role a) {
        r.add(a);
    }

    /**
     * Обновить роль.
     *
     * @param position место.
     * @param a        новая роль.
     */
    public void updateRole(int position, Role a) {
        r.update(position, a);
    }

    /**
     * Удалить роль.
     *
     * @param a роль.
     */
    public void deleteRole(Role a) {
        r.delete(a);
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
