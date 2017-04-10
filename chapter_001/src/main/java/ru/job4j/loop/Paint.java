package ru.job4j.loop;
/**
*Class Paint.
*
*@author Andrey Lemdyanov (mailto:lemdyanov5@mail.ru)
*@version $Id$
*@since 0.1
*/
public class Paint {
/**
*Метод рисует пирамиду.
*@param h высота пирамиды
*@return строка
*/
	public String piramid(int h) {
/**
*Объявляю переменные
*int k нужна для контроля зависимости ширины от высоты
*int n для определения количества пробелов
*/
		int k = 0;
        int n = h - 1;
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= h; i++) {

            for (int space = n; space > 0; space--) {
                str.append(" ");
            }
            for (int j = 1; j <= i + k; j++) {

                str.append("^");

            }
            for (int space = n; space > 0; space--) {
                str.append(" ");
            }
            n--;
            k++;
			if (i == h) {
				continue;
			}
            str.append(System.getProperty("line.separator"));
        }
        return str.toString();

    }
}