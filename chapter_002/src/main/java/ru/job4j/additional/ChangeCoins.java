package ru.job4j.additional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class ChangeCoins.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 06.06.2017
 */
public class ChangeCoins {
    /**
     * Поле сумма для размена.
     */
    private int cash;

    /**
     * Конструктор.
     *
     * @param cash сумма.
     */
    public ChangeCoins(int cash) {
        this.cash = cash;
    }

    /**
     * Поле для простого размена.
     */
    private List<Integer> list = new ArrayList<>(3);
    /**
     * Поле для всех возможных разменов.
     */
    private List<List<Integer>> list2 = new ArrayList<>();

    /**
     * Простой размен.
     * Поле rem - остаток.
     * ten - количество десяток.
     * five - количество пятерок.
     * one - количество единиц.
     *
     * @return список.
     */
    public List<Integer> changeSimple() {
        int rem, ten, five, one;

        ten = cash / 10;
        rem = cash % 10;
        five = rem / 5;
        rem = rem % 5;
        one = rem;


        list.add(ten);
        list.add(five);
        list.add(one);
        return list;
    }

    /**
     * Все возможные размены.
     *
     * @return список списков.
     */
    public List<List<Integer>> changeComplex() {
        for (int i = 0; i < cash / 10; i++) {
            for (int j = 0, k = cash - i * 10; k >= 0; j++, k -= 5) {
                list2.add(new ArrayList<Integer>(Arrays.asList(i, j, k)));
            }
        }
        return list2;
    }

    /**
     * Переопределение toString.
     * @return строка.
     */
    @Override
    public String toString() {
        return "Result: "
                + "cash = "
                + this.cash
                + "\nSimple exchange \nTotal10: "
                + this.list.get(0)
                + "\nTotal5: "
                + this.list.get(1)
                + "\nTotal1: "
                + this.list.get(2);
    }

    /**
     * Точка входа.
     * @param args массив строк.
     */
    public static void main(String[] args) {
        ChangeCoins simple = new ChangeCoins(109);
        simple.changeSimple();
//        ChangeCoins complex = new ChangeCoins(100);
//        System.out.println(complex.changeComplex());
        System.out.println(simple);
    }
}
