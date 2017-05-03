package ru.job4j.paint;

/**
 * Class Square.
 *
 * @author Andrey Lemdyanov
 * @since 03.05.2017
 */
public class Square implements Shape {
    /**
     * Метод рисует квадрат.
     *
     * @return строка.
     */
    public String pic() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(" ......%s", System.getProperty("line.separator")));
        sb.append(String.format(" .    .%s", System.getProperty("line.separator")));
        sb.append(String.format(" ......%s", System.getProperty("line.separator")));
        return sb.toString();
    }

}
