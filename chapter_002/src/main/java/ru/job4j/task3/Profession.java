package ru.job4j.task3;

/**
 * Class Profession.
 *
 * @author Andrey Lemdyanov
 * @since 20.04.2017
 */
public class Profession {
    /**
     * Объявляем поле name.
     */
    private String name;
    /**
     * Объявляем поле education.
     */
    private String education;
    /**
     * Объявляем поле experience.
     */
    private int experience;
    /**
     * Объявляем поле age.
     */
    private int age;

    /**
     * Конструктор по умолчанию.
     */
    public Profession() {
    }

    /**
     * Конструктор с 4 параметрами.
     *
     * @param name       имя
     * @param education  образование
     * @param experience опыт
     * @param age        возраст
     */
    public Profession(String name, String education, int experience, int age) {
        this.name = name;
        this.education = education;
        this.experience = experience;
        this.age = age;
    }

    /**
     * Геттер name.
     *
     * @return имя
     */
    public String getName() {
        return this.name;
    }

    /**
     * Геттер education.
     *
     * @return образование
     */
    public String getEducation() {
        return this.education;
    }

    /**
     * Геттер experience.
     *
     * @return опыт
     */
    public int getExperience() {
        return this.experience;
    }

    /**
     * Геттер age.
     *
     * @return возраст
     */
    public int getAge() {
        return this.age;
    }
}