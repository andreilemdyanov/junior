package ru.job4j.inspection;
/**
*Class Check.
*
*@author Andrey Lemdyanov (mailto:lemdyanov5@mail.ru)
*@version $Id$
*@since 0.1
*/
public class Check {
/**
*Метод проверяет является ли строка sub подстрокой origin.
*@param origin оригинальная строка
*@param sub подстрока
*@return result возвращает результфт проверки
*/
	 boolean contains(String origin, String sub) {
        char[] big = origin.toCharArray();
        char[] small = sub.toCharArray();
        int j = 0;
        boolean result = false;
        for (int i = 0; i < big.length; i++) {
            int count = 0;
            if (big[i] == small[j]) {
                count++;
                for (int n = i + 1, k = 1; (k < small.length) & n < big.length; k++, n++) {
                    if (big[n] == small[k]) {
                        count++;
                    }

                }
                if (count == small.length) {
                    result = true;
                }
            }
        }
        return result;
    }

}