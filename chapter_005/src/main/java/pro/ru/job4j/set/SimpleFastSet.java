package pro.ru.job4j.set;

/**
 * Class SimpleFastSet.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 06.07.2017
 */

/**
 * SimpleFastSet.
 *
 * @param <T> type.
 */
public class SimpleFastSet<T> extends SimpleArraySet<T> {
    /**
     * Переопределение checkSame.
     *
     * @param o элемент.
     * @return есть ли дубликат?
     */
    @Override
    public boolean checkSame(T o) {
        int lower = 0;
        int higher = this.getContainer().length - 1;
        int current;
        while (true) {
            if (higher == 0) {
                return false;
            } else if (this.getContainer()[0] == o) {
                return true;
            }
            current = (lower + higher) / 2;
            if (this.getContainer()[current] == null) {
                higher = current - 1;
                continue;
            }
            if (this.getContainer()[current].hashCode() == o.hashCode()) {
                return true;
            } else if (lower > higher) {
                return false;
            } else {
                if (this.getContainer()[current].hashCode() < o.hashCode()) {
                    lower = current + 1;
                } else {
                    higher = current - 1;
                }
            }
        }
    }

    /**
     * Точка входа.
     *
     * @param args массив строк.
     */
    public static void main(String[] args) {
        SimpleFastSet<Integer> set = new SimpleFastSet<>();
        long timeBefore = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            set.add(i);
        }
        long timeAfter = System.currentTimeMillis();
        long result = timeAfter - timeBefore;
        System.out.println("Set с бинарным поиском по хэшкоду: " + result);

        SimpleArraySet<Integer> arset = new SimpleArraySet<>();
        long timeB = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            arset.add(i);
        }
        long timeA = System.currentTimeMillis();
        long res = timeA - timeB;
        System.out.println("Обычный Set: " + res);
    }

}
