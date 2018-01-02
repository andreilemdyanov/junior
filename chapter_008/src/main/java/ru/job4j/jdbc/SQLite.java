package ru.job4j.jdbc;

/**
 * Class SQLite.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 23.12.2017
 */
public class SQLite {

    private String url;

    private int n;

    public SQLite() {
    }

    public String getUrl() {
        return url;
    }

    public int getN() {
        return n;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setN(int n) {
        this.n = n;
    }

}
