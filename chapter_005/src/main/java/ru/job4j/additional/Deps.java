package ru.job4j.additional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
    public Deps(List<String> deps) {
        this.deps = deps;
    }

    /**
     * Метод для прямой сортировки.
     *
     * @return deps.
     */
    public List<String> sortForward() {
        deps.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return deps;
    }

    /**
     * Метод для обратной сортировки.
     *
     * @return deps.
     */
    public List<String> sortBack() {
        deps.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -o1.compareTo(o2);
            }
        });
        return deps;
    }

    public static void main(String[] args) {
        Deps d = new Deps(new ArrayList<>(Arrays.asList("K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2", "K1", "K2\\SK1")));
        System.out.println(d.sortForward());
        System.out.println(d.sortBack());
    }
}
