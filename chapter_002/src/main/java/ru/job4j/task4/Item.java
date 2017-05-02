package ru.job4j.task4;

/**
 * Class Item.
 *
 * @author Andrey Lemdyanov
 * @since 24.04.2017
 */
public class Item {
    /**
     * Id.
     */
    private String id;
    /**
     * Имя.
     */
    private String name;
    /**
     * Описание.
     */
    private String desc;
    /**
     * Время создания.
     */
    private long create;

    /**
     * Конструктор по умолчанию.
     */
    public Item() {
    }

    /**
     * Конструктор с параметрами.
     *
     * @param name   имя.
     * @param desc   описание.
     * @param create время создания.
     */
    public Item(String name, String desc, long create) {
        this.name = name;
        this.desc = desc;
        this.create = create;
    }

    /**
     * Геттер name.
     *
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Геттер desc.
     *
     * @return desc.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Геттер create.
     *
     * @return create.
     */
    public long getCreate() {
        return this.create;
    }

    /**
     * Геттер id.
     *
     * @return id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Сеттер id.
     *
     * @param id строка записывается в id.
     */
    public void setId(String id) {
        this.id = id;
    }
}