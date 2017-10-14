package ru.job4j.monitore;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class ParallerSearch.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 10.10.2017
 */
public class ParallerSearch {
    /**
     * Список потоков.
     */
    private final List<Thread> threads = new ArrayList<>();
    /**
     * Список файлов, где содержится текст.
     */
    private final List<String> result = new ArrayList<>();

    /**
     * Метод для запуска поиска.
     *
     * @param root директория.
     * @param text слово.
     * @param exts расширения.
     */
    public void init(String root, String text, List<String> exts) {
        List<String> list = this.getAllDirectiories(root);
        this.searchAllFiles(list, text, exts);
    }

    /**
     * Метод возвращает все директории.
     *
     * @param root директория для поиска.
     * @return список директорий.
     */
    private List<String> getAllDirectiories(String root) {
        File rootDir = new File(root);
        List<String> directs = new ArrayList<>();
        Queue<File> fileTree = new PriorityQueue<>();

        Collections.addAll(fileTree, rootDir.listFiles());

        while (!fileTree.isEmpty()) {
            File currentFile = fileTree.remove();
            if (currentFile.isDirectory()) {
                Collections.addAll(fileTree, currentFile.listFiles());
                directs.add(currentFile.getAbsolutePath());
            }
        }
        return directs;
    }

    /**
     * Метод ищет все файлы по директориям.
     *
     * @param list список директорий.
     * @param text слово для поиска.
     * @param exts расширения файлов.
     */
    private void searchAllFiles(List<String> list, String text, List<String> exts) {
        for (String path : list) {
            File file = new File(path);
            File[] filesArray = file.listFiles();
            if (filesArray != null) {
                for (File d : filesArray) {
                    for (String ex : exts) {
                        if (d.getName().endsWith(ex)) {
                            synchronized (threads) {
                                threads.add(new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            List<String> lines = Files.readAllLines(Paths.get(d.getPath()), Charset.forName("windows-1251"));
                                            List<String> words = new ArrayList<>();
                                            for (String g : lines) {
                                                String[] t = g.split(" ");
                                                words.addAll(new ArrayList<>(Arrays.asList(t)));
                                            }
                                            for (String each : words) {
                                                synchronized (result) {
                                                    if (each.equals(text)) {
                                                        result.add(d.getPath());
                                                        break;
                                                    }
                                                }
                                            }
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }));
                            }
                        }
                    }
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
        ParallerSearch search = new ParallerSearch();
        search.init("D:\\Прочитанное", "сон", new ArrayList<>(Arrays.asList(".txt", ".fb2", ".doc")));
        for (Thread a : search.threads) {
            try {
                a.start();
                a.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(search.result);
    }
}
