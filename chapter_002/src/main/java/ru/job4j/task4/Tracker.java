package ru.job4j.task4;

import java.util.Random;
import java.util.Arrays;

/**
 * Class Tracker.
 *
 * @author Andrey Lemdyanov
 * @since 24.04.2017
 */

public class Tracker {
    /**
     * Объявляем массив типа Item.
     */
    private Item[] items = new Item[100];
    /**
     * Переменная счетчик.
     */
    private int position = 0;
    /**
     * Случайное число.
     */
    private static final Random RN = new Random();

    /**
     * Метод добавления элемента в массив.
     *
     * @param item элемент для добавления.
     * @return item добавленный элемент.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
        return item;
    }

    /**
     * Метод для замены элемента.
     *
     * @param item заменяющий элемент.
     */
    public void update(Item item) {
        for (int i = 0; i < this.position; i++) {
            if (this.items[i] != null && this.items[i].getId().equals(item.getId())) {
                this.items[i] = item;
                break;
            }
        }
    }

    /**
     * Метод для удаления элемента.
     *
     * @param item элемент который надо удалить.
     */
    public void delete(Item item) {
        for (int i = 0; i < this.position; i++) {
            if (i == position - 1) {
                this.items[i] = null;
                break;
            }
            if (item.getId().equals(this.items[i].getId())) {
                System.arraycopy(this.items, i + 1, this.items, i, position - i);
                this.items[position--] = null;
                break;
            }

        }
    }

    /**
     * Метод возвращает копию массива без null элементов.
     *
     * @return copy обработанный массив.
     */
    public Item[] findAll() {
        Item[] copy = new Item[this.position];
        int index = 0;
        for (Item item : this.items) {
            if (item != null) {
                copy[index++] = item;
            }
        }
        return Arrays.copyOf(copy, index);
    }

    /**
     * Метод ищет элемент по имени.
     *
     * @param key имя элемента для поиска.
     * @return result возвращает массив с совпадающими именами.
     */
    public Item[] findByName(String key) {
        Item[] result = new Item[this.position];
        int index = 0;
        for (Item item : this.items) {
            if (item == null) {
                continue;
            }
            if (key.equals(item.getName())) {
                result[index++] = item;
            }
        }
        return Arrays.copyOf(result, index);
    }

    /**
     * Метод для поиска элемента по id.
     *
     * @param id для поиска.
     * @return result возвращает найденный элемент или null.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Метод для генерации id.
     *
     * @return случайный id.
     */
    public String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Метод возвращает копию массива.
     *
     * @return result копия.
     */
    public Item[] getAll() {
        return this.items;
    }
}