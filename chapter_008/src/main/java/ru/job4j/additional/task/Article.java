package ru.job4j.additional.task;

import java.util.Calendar;

/**
 * Class Article.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 01.04.2018
 */
class Article {

    private String author;
    private String name;
    private String url;
    private String desc;
    private Calendar time;

    public Article(String author, String name, String url, String desc, Calendar time) {
        this.author = author;
        this.name = name;
        this.url = url;
        this.desc = desc;
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Article{" +
                "author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", time=" + time +
                '}' + '\n';
    }
}
