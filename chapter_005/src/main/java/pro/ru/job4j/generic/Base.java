package pro.ru.job4j.generic;

/**
 * Class Base.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 22.06.2017
 */
public abstract class Base {
    /**
     * Поле id.
     */
    private String id;

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
     * @param id ай ди.
     */
    public void setId(String id) {
        this.id = id;
    }
}
