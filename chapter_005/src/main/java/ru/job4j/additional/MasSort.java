package ru.job4j.additional;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Class MasSort.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 07.06.2017
 */
public class MasSort {
    /**
     * Массив.
     */
    private String[] mass = new String[]{"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
            "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};

    /**
     * Метод для добавления недостающих строк.
     */
    public void addFirst() {
        ArrayList<String> str = new ArrayList<>(Arrays.asList(mass));
        ArrayList<String> result = new ArrayList<>();
        result.addAll(str);
        boolean flag1 = true;
        boolean flag2 = true;

        for (String s : str) {

            String sub = s.substring(0, 2);
                if (!str.contains(sub)) {
                    while (flag1) {
                    result.add(sub);
                    flag1 = false;
                }
            }

                if (s.length() > 6) {
                    String sub2 = s.substring(0, 6);
                    if (!str.contains(sub2)) {
                        while (flag2) {
                        result.add(sub2);
                        flag2 = false;
                    }
                }
            }
        }

        mass = result.toArray(mass);
    }

    /**
     * Переопределение toString.
     * @return строка.
     */
    @Override
    public String toString() {
        return "MasSort{"
                + "mass="
                + Arrays.toString(mass)
                + '}';
    }

    /**
     * Точка входа.
     * @param args массив строк.
     */
    public static void main(String[] args) {
        MasSort m = new MasSort();
        System.out.println(m);
        m.addFirst();
        Arrays.sort(m.mass, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(m);
        Arrays.sort(m.mass, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println(m);
    }
}

