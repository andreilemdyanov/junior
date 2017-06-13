package ru.job4j.additional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class Deps.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 13.06.2017
 */
public class Deps {
    /**
     * Поле департаменты.
     */
    private List<String> deps;

    /**
     * Конструктор.
     */
    public Deps() {
        MasSort n = new MasSort();
        n.addFirst();
        List<String> d = new ArrayList<>(Arrays.asList(n.getMass()));
        this.deps = d;
    }

}
