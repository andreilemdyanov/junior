package ru.job4j.merge;
/**
 * Class MergeSort дополнительное задание.
 * @author Andrey Lemdyanov
 * @since 20.04.2017
*/
public class MergeSort {
/**
*Метод объединяет два массива в один отсортированный по возрастанию.
*@param first первый массива
*@param second второй массива
@return a результат
*/
	public int[] sort(int[] first, int[] second) {
		int[] mas = new int[first.length + second.length];
		int i = 0, j = 0;
		for (int k = 0; k < mas.length; k++) {
			if (i > first.length - 1) {
				mas[k] = second[j++];
			} else if (j > second.length - 1) {
				mas[k] = first[i++];
			} else if (first[i] < second[j]) {
				mas[k] = first[i++];
			} else {
				mas[k] = second[j++];
			}
		}
		return mas;
	}
}