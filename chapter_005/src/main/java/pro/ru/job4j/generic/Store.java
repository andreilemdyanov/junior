package pro.ru.job4j.generic;

/**
 * Interface Store.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 22.06.2017
 */
public interface Store<T extends Base> {
    /**
     * Добавить.
     *
     * @param t элемент.
     */
    void add(T t);

    /**
     * Вернуть лист.
     *
     * @return лист.
     */
    SimpleArray<T> get();

    /**
     * Лист в строковом виде.
     *
     * @return строка.
     */
    String forEach();

    /**
     * Обновить.
     *
     * @param a элемент.
     */
    void update(T a);

    /**
     * Удалить.
     *
     * @param a элемент.
     */
    void delete(T a);
}
