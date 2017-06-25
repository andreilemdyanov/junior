package pro.ru.job4j.generic;

import java.util.Arrays;

/**
 * Class SimpleArray.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 16.06.2017
 */
public class SimpleArray<T extends Base> {
    /**
     * Поле массив объектов.
     */
    private Object[] objects;

    /**
     * Геттер массива.
     *
     * @return массив.
     */
    public Object[] getObjects() {
        return objects;
    }

    /**
     * Поле индекс.
     */
    private int index = 0;

    /**
     * Геттер индекса.
     *
     * @return индекс.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Сеттер индекса
     *
     * @param index новый индекс.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * Конструктор.
     *
     * @param size размер массива.
     */
    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    /**
     * Метод печатает переданное значение.
     *
     * @param value значение.
     * @param <K>   тип.
     * @return значение.
     */
    public <K> K print(K value) {
        System.out.println(value);
        return value;
    }

    /**
     * Добавление.
     *
     * @param value элемент.
     */
    public void add(T value) {
        this.objects[index++] = value;
    }

    /**
     * Геттер элемента.
     *
     * @param position номер ячейки.
     * @return элемент.
     */
    public T get(int position) {
        return (T) this.objects[position];
    }

    /**
     * Обновление элемента.
     *
     * @param value новое значение.
     */
    public void update(T value) {
        int pos = 0;
        while (pos < objects.length) {
            if (((T) objects[pos]).getId().equals(value.getId())) {
                objects[pos] = value;
            }
            pos++;
        }
    }

    /**
     * Удаление элемента.
     *
     * @param value значение.
     */
    public void delete(T value) {
        int pos = 0;
        for (Object o : this.objects) {
            if (o.equals(value)) {
                Object[] temp = new Object[this.getIndex() - 1];
                System.arraycopy(this.objects, 0, temp, 0, pos);
                System.arraycopy(this.objects, pos + 1, temp, pos, this.objects.length - pos - 1);
                this.objects = temp;
                this.setIndex(index - 1);
                break;
            }
            pos++;
        }
    }

    /**
     * Переопределение toString.
     *
     * @return строка.
     */
    @Override
    public String toString() {
        return "SimpleArray{"
                + "objects="
                + Arrays.toString(objects)
                + ", index="
                + index
                + '}';
    }

    /**
     * Переопределение equals.
     *
     * @param o объект.
     * @return равны?
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleArray<?> that = (SimpleArray<?>) o;

        if (index != that.index) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(objects, that.objects);
    }

    /**
     * Переопределение hashcode.
     *
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        int result = Arrays.hashCode(objects);
        result = 31 * result + index;
        return result;
    }
}
