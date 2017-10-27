package ru.job4j.monitore;

import ru.job4j.wait.ProducerCustomer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class ParallerSearch.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 28.10.2017
 */
public class ParallerSearch {
    /**
     * Список потоков.
     */
    private final ProducerCustomer<File> queue;
    /**
     * Муляж для окончания.
     */
    private final File dummy;
    /**
     * Количество потоков.
     */
    private final int searchThreads;
    /**
     * Лист результат.
     */
    private final List<String> result;

    /**
     * Конструктор.
     */
    public ParallerSearch() {
        queue = new ProducerCustomer<>();
        dummy = new File("");
        searchThreads = 100;
        result = new ArrayList<>();
    }

    /**
     * Точка входа.
     * @param args массив строк.
     * @throws InterruptedException исключение.
     */
    public static void main(String[] args) throws InterruptedException {
        ParallerSearch p = new ParallerSearch();
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Введите путь к папке : ");
            String directory = in.nextLine();
            System.out.print("Введите слово для поиска : ");
            String keyword = in.nextLine();
            System.out.print("Введите расширения файлов для поиска (через запятую) : ");
            List<String> exts = new ArrayList<>(Arrays.asList(in.nextLine().replaceAll(" ", "").split(",")));

            Runnable enumerator = () -> {
                try {
                    p.enumerate(new File(directory), exts);
                    p.queue.put(p.dummy);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };

            Thread a = new Thread(enumerator);
            a.start();
            a.join();
            for (int i = 1; i <= p.searchThreads; i++) {
                Runnable searcher = () -> {
                    try {
                        boolean done = false;
                        while (!done) {
                            File file = p.queue.take();
                            if (file == p.dummy) {
                                p.queue.put(file);
                                done = true;
                            } else {
                                p.search(file, keyword);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                };
                Thread b = new Thread(searcher);
                b.start();
                b.join();

            }
        }
        System.out.println(p.result);
    }

    /**
     * Метод помещает файл в очередь.
     * @param directory директория.
     * @param exts расширения.
     * @throws InterruptedException исключение.
     */
    public void enumerate(File directory, List<String> exts) throws InterruptedException {
        File[] files = directory.listFiles();
        for (File file : files) {
            for (String ex : exts) {
                if (file.getName().endsWith(ex)) {
                    if (file.isDirectory()) {
                        enumerate(file, exts);
                    } else {
                        queue.put(file);
                    }
                }
            }
        }
    }

    /**
     * Поиск слова в файле.
     * @param file файл.
     * @param keyword слово.
     * @throws IOException исключение.
     */
    public void search(File file, String keyword) throws IOException {
        List<String> lines = new ArrayList<>();
        List<String> words = new ArrayList<>();
        try (Scanner in = new Scanner(file, "windows-1251")) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                lines.add(line);
            }
            for (String g : lines) {
                String[] t = (g + " ").split("\\p{P}?[ \\t\\n\\r]+");
                words.addAll(new ArrayList<>(Arrays.asList(t)));
            }
            for (String each : words) {
                synchronized (result) {
                    if (each.equals(keyword)) {
                        result.add(file.getPath());
                        break;
                    }
                }
            }
        }
    }
}

