package ru.job4j.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * class ConvertList.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConvertList {
    /**
     * Поле списка.
     */
    List<Integer> list;

    /**
     * Конструктор.
     *
     * @param list список.
     */
    public ConvertList(List<Integer> list) {
        this.list = list;
    }

    /**
     * Метод для записи двумерного массива в список.
     *
     * @param array массив.
     * @return список.
     */
    public List<Integer> toList(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                list.add(array[i][j]);
            }
        }
        return list;
    }

    /**
     * Метод для записи списка в двумерный массив с указанием размера.
     *
     * @param list список.
     * @param rows количство строк.
     * @return массив.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        Iterator<Integer> iter = list.iterator();
        int zeroNum = list.size() % rows;
        int size;
        if (zeroNum != 0) {
            size = (list.size() / rows) + 1;
        } else {
            size = list.size() / rows;
        }

        int[][] arr = new int[rows][size];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < size; j++) {
                if (iter.hasNext()) {
                    arr[i][j] = iter.next();
                } else {
                    arr[i][j] = 0;
                }
            }
        }
        return arr;
    }

    /**
     * Метод для конвертации листа массивов в один лист Integer.
     * @param list лист массивов.
     * @return лист.
     */
    public List<Integer> convert (List<int[]> list) {
        List<Integer> res = new ArrayList<>();
        for (int[] mas : list) {
            for (int num : mas) {
                res.add(num);
            }
        }
        return res;
    }

    /**
     * Точка входа.
     *
     * @param args массив строк.
     */
    public static void main(String[] args) {
        ConvertList conlist = new ConvertList(new ArrayList<>());
        int[][] ar = {{1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}};
        List<Integer> list = conlist.toList(ar);
        System.out.println(list);
        int[][] mas = conlist.toArray(list, 7);
        System.out.println(Arrays.deepToString(mas));
        ConvertList second = new ConvertList(new ArrayList<>());
        List<int[]> slist = new ArrayList<>();
        slist.add(new int[]{1, 2});
        slist.add(new int[]{3, 4, 5, 6});
        slist.add(new int[]{7, 8, 9, 10, 11, 12});
        List<Integer> result = second.convert(slist);
        System.out.println(result);
    }
}
