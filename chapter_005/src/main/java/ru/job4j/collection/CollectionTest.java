package ru.job4j.collection;

import java.util.*;

/**
 * Class CollectionTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class CollectionTest {
    /**
     * Поле коллекции.
     */
    Collection<String> collection;

    /**
     * Метод для добавления строк.
     *
     * @param collection коллекция
     * @param line       строка
     * @param amount     число добавлений
     * @return затраченное время
     */
    public long add(Collection<String> collection, String line, int amount) {
        long before = System.currentTimeMillis();
        int i;
        for (i = 0; i < amount; i++) {
            collection.add(line + i);
        }
        this.collection = collection;
        long after = System.currentTimeMillis();
        return after - before;
    }

    /**
     * Метод для удаления n строк из начала коллекции.
     *
     * @param collection коллекция
     * @param amount     число удалений
     * @return затраченное время
     */
    public long delete(Collection<String> collection, int amount) {
        long before = System.currentTimeMillis();
        Iterator<String> iter = collection.iterator();
        int i = 0;
        while (i < amount) {
            iter.next();
            iter.remove();
            i++;
        }
        long after = System.currentTimeMillis();
        return after - before;
    }

    /**
     * Точка входа.
     *
     * @param args массив строк
     */
    public static void main(String[] args) {
        CollectionTest test = new CollectionTest();
        ArrayList<String> alist = new ArrayList<>();
        long result = test.add(alist, "Строка", 1000000);
        System.out.println(String.format("Время вставки ArrayList : %d ", result));
        long delete = test.delete(alist, 45000);
        System.out.println(String.format("Время удаления ArrayList : %d ", delete));

        CollectionTest test2 = new CollectionTest();
        LinkedList<String> llist = new LinkedList<>();
        result = test2.add(llist, "Строка", 1000000);
        System.out.println(String.format("Время вставки LinkedList : %d", result));
        delete = test2.delete(llist, 45000);
        System.out.println(String.format("Время удаления LinkedList : %d ", delete));

        CollectionTest test3 = new CollectionTest();
        TreeSet<String> tlist = new TreeSet<>();
        result = test3.add(tlist, "Строка", 1000000);
        System.out.println(String.format("Время вставки TreeSet : %d", result));
        delete = test3.delete(tlist, 45000);
        System.out.println(String.format("Время удаления TreeSet : %d ", delete));
    }
}
