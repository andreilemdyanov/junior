package pro.ru.job4j.generic;

/**
 * Class UserStore.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 22.06.2017
 */
public class UserStore<T> implements Store<User> {
    /**
     * Поле контейнер.
     */
    private SimpleArray<User> u;

    /**
     * Геттер контейнера.
     *
     * @return контейнер.
     */
    public SimpleArray<User> getUser() {
        return u;
    }

    /**
     * Метод для отображения объектов по id.
     *
     * @return строка.
     */
    public String forEach() {
        String s = "";
        for (Object r : u.getObjects()) {
            s += "id: " + ((User) r).getId() + " ";
        }
        return s;
    }

    /**
     * Конструктор.
     *
     * @param u контейнер.
     */
    public UserStore(SimpleArray<User> u) {
        this.u = u;
    }

    /**
     * Добавить пользователя.
     *
     * @param a пользователь.
     */
    public void addUser(User a) {
        u.add(a);
    }

    /**
     * Обновить пользователя.
     *
     * @param position место.
     * @param a        новый пользователь.
     */
    public void updateUser(int position, User a) {
        u.update(position, a);
    }

    /**
     * Удалить пользователя.
     *
     * @param a пользователь.
     */
    public void deleteUser(User a) {
        u.delete(a);
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
