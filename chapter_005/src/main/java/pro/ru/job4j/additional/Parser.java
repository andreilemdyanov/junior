package pro.ru.job4j.additional;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class Parser.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 29.08.2017
 */
public class Parser {
    /**
     * Контейнер для книг.
     */
    private Map<String, HashMap<Integer, Order>> books = new HashMap<>();

    /**
     * Метод считывает файл по строке.
     *
     * @param path путь к файлу.
     * @throws IOException исключение.
     */
    public void load(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("<A")) {
                Order order = this.parse(line, true);
                HashMap<Integer, Order> list = books.get(order.getBook());
                if (list == null) {
                    list = new HashMap<>();
                    books.put(order.getBook(), list);
                }
                list.put(order.getId(), order);
            } else if (line.startsWith("<D")) {
                Order order = this.parse(line, false);
                books.get(order.getBook()).remove(order.getId());
            }
        }
    }

    /**
     * Метод для разбора строки.
     *
     * @param line строка.
     * @param add  добавить или удалить?
     * @return заявка.
     */
    public Order parse(String line, boolean add) {
        String[] values = new String[5];
        int pos = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '"') {
                int temp = i;
                do {
                    i++;
                } while (line.charAt(i) != '"');
                values[pos++] = line.substring(temp + 1, i);
            }
        }
        if (add) {
            return new Order(values[0], "BUY".equals(values[1]) ? Order.Type.BUY : Order.Type.SELL,
                    Float.valueOf(values[2]), Integer.valueOf(values[3]), Integer.valueOf(values[4]));
        } else {
            return new Order(values[0], Integer.valueOf(values[1]));
        }
    }

    /**
     * Точка входа.
     *
     * @param args массив строк.
     * @throws IOException исключение.
     */
    public static void main(String[] args) throws IOException {
        long before = System.currentTimeMillis();

        Parser p = new Parser();
        p.load("C:\\Users\\DNS\\Downloads\\orders.xml");
        Sort sort = new Sort(p.books.get("book-1").values());
        System.out.println("BOOK-1");
        sort.calculate();
        Sort sort2 = new Sort(p.books.get("book-2").values());
        System.out.println("BOOK-2");
        sort2.calculate();
        Sort sort3 = new Sort(p.books.get("book-3").values());
        System.out.println("BOOK-3");
        sort3.calculate();

        System.out.printf("Time :%s", System.currentTimeMillis() - before);

    }
}
