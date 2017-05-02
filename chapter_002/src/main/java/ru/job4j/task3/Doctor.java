package ru.job4j.task3;

/**
 * Class Doctor.
 *
 * @author Andrey Lemdyanov
 * @since 20.04.2017
 */
public class Doctor extends Profession {
    /**
     * Конструктор с 4 параметрами.
     *
     * @param name       имя
     * @param education  образование
     * @param experience опыт
     * @param age        возраст
     */
    public Doctor(String name, String education, int experience, int age) {
        super(name, education, experience, age);
    }

    /**
     * Метод лечить.
     *
     * @param pacient пациент
     * @return строку о том что доктор лечит пациента.
     */
    public String heal(Pacient pacient) {
        return String.format("%s лечит %s", this.getName(), pacient.getName());
    }

    /**
     * Метод выписать рецепт.
     *
     * @return рецепт.
     */
    public String writingTheRecipe() {
        return "Вот Ваш рецепт...";
    }

    /**
     * Метод поставить диагноз.
     *
     * @return диагноз.
     */
    public String toDiagnose() {
        return "Ваш диагноз...";
    }
}