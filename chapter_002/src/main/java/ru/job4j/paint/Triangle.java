package ru.job4j.paint;

/**
 * Class Triangle.
 *
 * @author Andrey Lemdyanov
 * @since 03.05.2017
 */
public class Triangle implements Shape {
    /**
     * Метод рисует треугольник.
     *
     * @return строка.
     */
    public String pic() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("    .%s", System.getProperty("line.separator")));
        sb.append(String.format("  .   .%s", System.getProperty("line.separator")));
        sb.append(String.format(" .......%s", System.getProperty("line.separator")));
        return sb.toString();
    }
}
