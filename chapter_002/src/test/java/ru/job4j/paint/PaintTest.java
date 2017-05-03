package ru.job4j.paint;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class PaintTest.
 *
 * @author Andrey Lemdyanov
 * @since 03.05.2017
 */
public class PaintTest {
    /**
     * Метод тестирует прорисовку квадрата.
     */
    @Test
    public void whenDrawSquare() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Paint paint = new Paint();
        paint.draw(new Square());
        assertThat(
                out.toString(),
                is(
                        String.format(
                                " ......%s .    .%<s ......%<s",
                                System.getProperty("line.separator")
                        )
                )
        );
    }

    /**
     * Метод тестирует прорисовку треугольника.
     */
    @Test
    public void whenDrawTriangle() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Paint paint = new Paint();
        paint.draw(new Triangle());
        assertThat(
                out.toString(),
                is(
                        String.format(
                                "    .%s  .   .%<s .......%<s",
                                System.getProperty("line.separator")
                        )
                )
        );
    }
}
