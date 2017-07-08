package pro.ru.job4j.map;

import java.util.Arrays;

/**
 * Class SimpleMap.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleMap<K, V> {

    Object[] container;
    int position = 0;

    public SimpleMap() {
        this.container = new Object[10];
    }

    public SimpleMap(int size) {
        this.container = new Object[size];
    }

    public Object[] getContainer() {
        return container;
    }

    public class Pair {
        public final K key;
        public final V value;

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }


    }

    private void loadF() {
        if (container.length * 0.75 <= position) {
            SimpleMap<K, V> m = new SimpleMap<>(container.length * 2);
            for (Object a : container) {
                if (a != null) {
                    m.insert(((Pair) a).getKey(), (((Pair) a).getValue()));
                }
            }
            this.container = m.getContainer();
        }
    }

    private int hash(Object key) {
        int h = key.hashCode();
        return h ^ (h >>> 16);
    }

    boolean insert(K key, V value) {
        this.loadF();
        int index =
                this.hash(key) % (this.getContainer().length - 1);
        System.out.println("insert index " + index);

        if (container[index] != null) {
            return false;
        }
        container[index] = new Pair(key, value);
        position++;
        return true;
    }

    V get(K key) {
        int index = key.hashCode() & (this.getContainer().length - 1);
        System.out.println("get index " + index);
        if (index > container.length) {
            return null;
        }
        if (container[index] != null && ((Pair) container[index]).getKey().hashCode() == key.hashCode()) {
            return ((Pair) container[index]).getValue();
        } else return null;

    }

    public static void main(String[] args) {
        SimpleMap<String, String> map = new SimpleMap<>();
        map.insert("1", "R");
        map.insert("fg", "R");
        map.insert("Fghy", "R");
        map.insert("fffr", "R");
        map.insert("3r3r", "Y");
        map.insert("kdgudfhgkldf", "Rad");
        map.insert("ffgdgfr", "R");
        map.insert("kgottg", "Rxv");
        map.insert("fffryuu", "R");


        System.out.println(map.get("3r3r"));
        System.out.println(Arrays.toString(map.getContainer()));
    }

}
