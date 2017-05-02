package ru.job4j.task3;

/**
 * Class Pacient.
 *
 * @author Andrey Lemdyanov
 * @since 20.04.2017
 */
public class Pacient {
    /**
     * Имя пациента.
     */
    private String name;

    /**
     * Геттер имени.
     *
     * @return имя.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Конструкор по умолчанию.
     */
    public Pacient() {
    }

    /**
     * Конструктор.
     *
     * @param name имя.
     */
    public Pacient(String name) {
        this.name = name;
    }
}