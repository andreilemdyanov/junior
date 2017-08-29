package pro.ru.job4j.additional;

/**
 * Class Order.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 29.08.2017
 */
public class Order {
    /**
     * Тип.
     */
    public enum Type {
        /**
         * Покупка.
         */
        SELL,
        /**
         * Продажа.
         */
        BUY;
    }

    /**
     * Книга.
     */
    private String book;
    /**
     * Покупка или продажа.
     */
    private Type operation;
    /**
     * Цена.
     */
    private float price;
    /**
     * Количество заявок.
     */
    private int volume;
    /**
     * id заявки.
     */
    private int id;

    /**
     * Геттер количества заявок.
     *
     * @return количество.
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Геттер книги.
     *
     * @return книга.
     */
    public String getBook() {
        return book;
    }

    /**
     * Геттер типа.
     *
     * @return тип.
     */
    public Type getOperation() {
        return operation;
    }

    /**
     * Геттер цены.
     *
     * @return цена.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Геттер id.
     *
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Сеттер количества.
     *
     * @param volume количество.
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * Конструктор.
     *
     * @param book      книга.
     * @param operation тип.
     * @param price     цена.
     * @param volume    количество.
     * @param id        id.
     */
    public Order(String book, Type operation, float price, int volume, int id) {
        this.book = book;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
        this.id = id;
    }

    /**
     * Перегруженный конструктор.
     *
     * @param book книга.
     * @param id   id.
     */
    public Order(String book, int id) {
        this.book = book;
        this.id = id;
        this.operation = Type.BUY;
        this.price = 0f;
        this.volume = 0;
    }
}
