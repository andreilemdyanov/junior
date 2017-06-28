package ru.job4j.additional;

import java.util.ArrayList;
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
     * Геттер листа.
     *
     * @return deps.
     */
    public List<String> getDeps() {
        return deps;
    }

    /**
     * Конструктор.
     *
     * @param deps лист.
     */
    public Deps(List<String> deps) {
        this.deps = deps;
    }

    /**
     * Метод для добавления недостающих строк.
     */
    public void addFirst() {
        ArrayList<String> result = new ArrayList<>();
        result.addAll(deps);
        boolean flag1 = true;
        boolean flag2 = true;

        for (String s : deps) {

            String sub = s.substring(0, 2);
            if (!deps.contains(sub)) {
                while (flag1) {
                    result.add(sub);
                    flag1 = false;
                }
            }

            if (s.length() > 6) {
                String sub2 = s.substring(0, 6);
                if (!deps.contains(sub2)) {
                    while (flag2) {
                        result.add(sub2);
                        flag2 = false;
                    }
                }
            }
        }

        deps = result;
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

    /**
     * Метод для обратной сортировки с учетом длины строки.
     *
     * @return deps.
     */
    public List<String> sortAll() {
        deps.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = 0;
                int left = o1.length();
                int right = o2.length();
                int size = left > right ? left : right;
                for (int index = 0; index != size; index++) {
                    if (left > index && right > index) {
                        result = -String.valueOf(o1.charAt(index)).compareTo(String.valueOf(o2.charAt(index)));
                        if (result != 0) {
                            break;
                        }
                    } else if (left > index) {
                        result = 1;
                        break;
                    } else {
                        result = -1;
                        break;
                    }
                }
                return result;
            }
        });

        return deps;
    }

}
