package ru.job4j.jdbc.tracker;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Class Item.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 26.12.2017
 */
public class Item {
    /**
     * id.
     */
    private int id;
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
    private Timestamp create;

    /**
     * Конструктор по умолчанию.
     */
    public Item() {
    }

    /**
     * Конструктор с параметрами.
     *
     * @param name имя.
     * @param desc описание.
     */
    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    /**
     * Конструктор с параметрами.
     *
     * @param id   id.
     * @param name имя.
     * @param desc описание.
     */
    public Item(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
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
     * Сеттер name.
     *
     * @param name имя.
     */
    public void setName(String name) {
        this.name = name;
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
     * Сеттер desc.
     *
     * @param desc описание.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Геттер create.
     *
     * @return create.
     */
    public Timestamp getCreate() {
        return this.create;
    }

    /**
     * Сеттер create.
     *
     * @param create время создания.
     */
    public void setCreate(Timestamp create) {
        this.create = create;
    }

    /**
     * Геттер id.
     *
     * @return id.
     */

    public int getId() {
        return this.id;
    }


    /**
     * Сеттер id.
     *
     * @param id индекс.
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Item{"
                + "id="
                + id
                + ", name='"
                + name
                + '\''
                + ", desc='"
                + desc
                + '\''
                + ", create="
                + create
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return id == item.id
                && Objects.equals(name, item.name)
                && Objects.equals(desc, item.desc)
                && Objects.equals(create, item.create);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, create);
    }
}


