package ru.job4j.task5;

/**
 * Abstract class BaseAction.
 *
 * @author Andrey Lemdyanov
 * @since 12.05.2017
 */

public abstract class BaseAction implements UserAction {
    /**
     * Поле name.
     */
    String name;
    /**
     * Поле key.
     */
    int key;

    /**
     * Конструктор.
     *
     * @param name имя.
     * @param key  ключ.
     */
    public BaseAction(String name, int key) {
        this.name = name;
        this.key = key;
    }

    /**
     * Метод выводит строку меню.
     *
     * @return строка.
     */
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }


}
