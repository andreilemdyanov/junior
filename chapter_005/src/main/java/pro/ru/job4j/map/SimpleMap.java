package pro.ru.job4j.map;

import java.util.Iterator;

/**
 * Class SimpleMap.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 13.07.2017
 */

/**
 * SimpleMap.
 *
 * @param <K> type first.
 * @param <V> type second.
 */
public class SimpleMap<K, V> implements Iterable {
    /**
     * Контейнер.
     */
    private Object[] container;
    /**
     * Количество элементов в контейнере.
     */
    private int position = 0;

    /**
     * Конструктор.
     */
    public SimpleMap() {
        this.container = new Object[10];
    }

    /**
     * Конструктор с параметром.
     *
     * @param size размер контейнера.
     */
    public SimpleMap(int size) {
        this.container = new Object[size];
    }

    /**
     * Геттер контейнера.
     *
     * @return контейнер.
     */
    public Object[] getContainer() {
        return container;
    }

    /**
     * Внутренний класс.
     */
    class Pair {
        /**
         * Ключ.
         */
        private final K key;
        /**
         * Значение.
         */
        private final V value;

        /**
         * Геттер ключа.
         *
         * @return ключ.
         */
        public K getKey() {
            return key;
        }

        /**
         * Геттер значения.
         *
         * @return значение.
         */
        public V getValue() {
            return value;
        }

        /**
         * Конструктор.
         *
         * @param key   ключ.
         * @param value значение.
         */
        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Переопределение toString.
         *
         * @return строка.
         */
        @Override
        public String toString() {
            return "Pair{"
                    + "key="
                    + key
                    + ", value="
                    + value
                    + '}';
        }
    }

    /**
     * Метод, расширяющий контейнер.
     */
    private void loadF() {
        if (container.length * 0.75 < position) {
            SimpleMap<K, V> m = new SimpleMap<>(container.length * 2);
            for (Object a : container) {
                if (a != null) {
                    m.insert(((Pair) a).getKey(), (((Pair) a).getValue()));
                }
            }
            this.container = m.getContainer();
            this.position = m.position;
        }
    }

    /**
     * Генерация хэшкода по ключу.
     *
     * @param key ключ.
     * @return хэшкод.
     */
    private int hash(Object key) {
        int h = key.hashCode();
        return Math.abs(h ^ (h >>> 16));
    }

    /**
     * Вставить объект.
     *
     * @param key   ключ.
     * @param value значение.
     * @return Объект вставлен?
     */
    public boolean insert(K key, V value) {
        this.loadF();
        int index = this.hash(key) % (container.length - 1);
        if (container[index] != null) {
            return false;
        }
        container[index] = new Pair(key, value);
        position++;
        return true;
    }

    /**
     * Геттер значения по ключу.
     *
     * @param key ключ.
     * @return значение.
     */
    public V get(K key) {
        int index = this.hash(key) % (this.getContainer().length - 1);
        if (index > container.length - 1) {
            return null;
        }
        if (container[index] != null && ((Pair) container[index]).getKey().hashCode() == key.hashCode()) {
            return ((Pair) container[index]).getValue();
        } else {
            return null;
        }

    }

    /**
     * Удаление объекта по ключу.
     *
     * @param key ключ.
     * @return Объект удален?
     */
    public boolean delete(K key) {
        int index = this.hash(key) % (this.getContainer().length - 1);
        if (index > container.length - 1) {
            return false;
        }
        container[index] = null;
        position--;
        for (int i = index; i < container.length - 1; i++) {
            container[i] = container[i + 1];
        }
        return true;
    }

    /**
     * Итератор.
     *
     * @return итератор.
     */
    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int count = 0;
            private int pos = 0;
            private Pair that;

            @Override
            public boolean hasNext() {
                return position > count;
            }

            @Override
            public Object next() {
                for (int i = pos; i < container.length; i++) {
                    if (container[i] != null) {
                        that = (Pair) container[i];
                        pos = i + 1;
                        count++;
                        break;
                    }
                }
                return that;
            }
        };
    }

}
