package ru.job4j.task4;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class Tracker.
 *
 * @author Andrey Lemdyanov
 * @since 24.04.2017
 */

public class Tracker {
    /**
     * Объявляем список типа Item.
     */
    private ArrayList<Item> items = new ArrayList<>();
    /**
     * Случайное число.
     */
    private static final Random RN = new Random();

    /**
     * Метод добавления элемента в список.
     *
     * @param item элемент для добавления.
     * @return item добавленный элемент.
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        items.add(item);
        return item;
    }

    /**
     * Метод для замены элемента.
     *
     * @param item заменяющий элемент.
     */
    public void update(Item item) {
        int count = 0;
        for (Item i : items) {
            if (i.getId().equals(item.getId())) {
                items.set(count, item);
            }
            count++;
        }
    }

    /**
     * Метод для удаления элемента.
     *
     * @param item элемент который надо удалить.
     */
    public void delete(Item item) {
        items.remove(item);

    }

    /**
     * Метод возвращает копию списка без null элементов.
     *
     * @return обработанный список.
     */
    public ArrayList<Item> findAll() {
        items.trimToSize();
        return items;
    }

    /**
     * Метод ищет элемент по имени.
     *
     * @param key имя элемента для поиска.
     * @return result возвращает список с совпадающими именами.
     */
    public ArrayList<Item> findByName(String key) {
        ArrayList<Item> result = new ArrayList<>();
        for (Item item : items) {

            if (key.equals(item.getName())) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Метод для поиска элемента по id.
     *
     * @param id для поиска.
     * @return result возвращает найденный элемент или null.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item.getId().equals(id)) {
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
     * Метод возвращает копию списка.
     *
     * @return копия.
     */
    public ArrayList<Item> getAll() {
        return items;
    }
}