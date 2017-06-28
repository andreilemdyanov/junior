package pro.ru.job4j.generic;

/**
 * Class BaseStore.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 25.06.2017
 */

/**
 * BaseStore.
 *
 * @param <T> type.
 */
public abstract class BaseStore<T extends Base> implements Store<T> {

    /**
     * Поле лист.
     */
    private SimpleArray<T> u;

    /**
     * Конструктор.
     *
     * @param u лист.
     */
    BaseStore(SimpleArray<T> u) {
        this.u = u;
    }

    /**
     * Добавление элемента.
     *
     * @param a элемент.
     */
    public void add(T a) {
        u.add(a);
    }

    /**
     * Геттер листа.
     *
     * @return лист.
     */
    public SimpleArray<T> get() {
        return u;
    }

    /**
     * Представление в строковом виде.
     *
     * @return строка.
     */
    public String forEach() {
        String s = "";
        for (Object r : u.getObjects()) {
            s += "id: " + ((T) r).getId() + " ";
        }
        return s;
    }

    /**
     * Обновление элемента с таким же id.
     *
     * @param a новый элемент.
     */
    public void update(T a) {
        u.update(a);
    }

    /**
     * Удаление элемента.
     *
     * @param a элемент.
     */
    public void delete(T a) {
        u.delete(a);
    }
}
