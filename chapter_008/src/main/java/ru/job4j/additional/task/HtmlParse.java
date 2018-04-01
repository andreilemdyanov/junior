package ru.job4j.additional.task;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Class HtmlParse.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 01.04.2018
 */
public class HtmlParse {

    private static final Logger log = LoggerFactory.getLogger(HtmlParse.class);

    private DBWork dbWork;

    public HtmlParse() {
        dbWork = new DBWork();
    }

    public void start() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(new Runnable() {

            @Override
            public void run() {
                try {
                    work();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                } catch (ParseException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }, 0, 24, TimeUnit.HOURS);
    }

    public void start(int hours) {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(new Runnable() {

            @Override
            public void run() {
                try {
                    work();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                } catch (ParseException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }, 0, hours, TimeUnit.HOURS);
    }

    public Calendar getCalendarDate(Element element) {

        String date = element.select(".altCol").get(1).text();

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yy, HH:mm");//задаю формат даты

        Calendar cal;
        if (date.contains("сегодня")) {
            cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(date.substring(9, 11)));
            cal.set(Calendar.MINUTE, Integer.parseInt(date.substring(12, 14)));
        } else if (date.contains("вчера")) {
            cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(date.substring(7, 9)));
            cal.set(Calendar.MINUTE, Integer.parseInt(date.substring(10, 12)));
            cal.add(Calendar.DATE, -1);
        } else {
            cal = Calendar.getInstance();
            Date date1 = null;
            try {
                date1 = formatter.parse(date);
            } catch (ParseException e) {
                log.error(e.getMessage(), e);
            }
            cal.setTime(date1);
        }
        return cal;
    }

    public List scanHtmlPerDay() throws IOException {
        Document docTemp = Jsoup.connect("http://sql.ru/forum/job-offers").get();
        List<Article> list = new ArrayList<>();
        int numberPage = 1;
        Calendar temp = Calendar.getInstance();
        while (temp.get(Calendar.DATE) == Calendar.getInstance().get(Calendar.DATE)) {
            Elements aElems = docTemp.getElementsByAttributeValue("class", "forumTable");
            Elements elems = aElems.select("tr");

            for (Element el : elems) {
                Element aElement = el.attr("class", "postslisttopic");
                if ((aElement.select(".postslisttopic").text().contains("Java") || aElement.select(".postslisttopic").text().contains("JAVA") || aElement.select(".postslisttopic").text().contains("java"))
                        && (!((aElement.select(".postslisttopic").text().contains("Script")) || (aElement.select(".postslisttopic").text().contains("script"))))) {
                    Calendar cal = getCalendarDate(aElement);
                    temp = cal;

                    String author = aElement.select(".altCol a").text();

                    String url = aElement.select(".postslisttopic a").attr("href");

                    String name = aElement.select(".postslisttopic a").text();

                    Document docIn = Jsoup.connect(url).get();
                    Elements desc = docIn.getElementsByAttributeValue("class", "msgTable").first().getElementsByAttributeValue("class", "msgBody");
                    String s = Jsoup.parse(desc.get(1).html()).text();
                    Article art = new Article(author, name, url, s, cal);
                    list.add(art);
                    dbWork.insertNewArticle(art);
                    dbWork.insertNewVacanci(art);
                }
            }
            docTemp = Jsoup.connect("http://sql.ru/forum/job-offers/" + ++numberPage).get();
        }
        return list;
    }

    public List scanHtmlPerYear() throws IOException, ParseException {
        Document docTemp = Jsoup.connect("http://sql.ru/forum/job-offers").get();
        List<Article> list = new ArrayList<>();
        int numberPage = 1;
        Calendar temp = Calendar.getInstance();
        while (temp.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {
            Elements aElems = docTemp.getElementsByAttributeValue("class", "forumTable");
            Elements elems = aElems.select("tr");

            for (Element el : elems) {
                Element aElement = el.attr("class", "postslisttopic");

                if ((aElement.select(".postslisttopic").text().contains("Java") || aElement.select(".postslisttopic").text().contains("JAVA") || aElement.select(".postslisttopic").text().contains("java"))
                        && (!((aElement.select(".postslisttopic").text().contains("Script")) || (aElement.select(".postslisttopic").text().contains("script"))))) {
                    Calendar cal = this.getCalendarDate(aElement);
                    temp = cal;

                    String author = aElement.select(".altCol a").text();

                    String url = aElement.select(".postslisttopic a").attr("href");

                    String name = aElement.select(".postslisttopic a").text();

                    Document docIn = Jsoup.connect(url).get();
                    Elements desc = docIn.getElementsByAttributeValue("class", "msgTable").first().getElementsByAttributeValue("class", "msgBody");
                    String s = Jsoup.parse(desc.get(1).html()).text();

                    if (temp.get(Calendar.YEAR) != Calendar.getInstance().get(Calendar.YEAR)) {
                        break;
                    }
                    Article art = new Article(author, name, url, s, cal);
                    list.add(art);
                    dbWork.insertNewArticle(art);
                    dbWork.insertNewVacanci(art);
                }
            }
            docTemp = Jsoup.connect("http://sql.ru/forum/job-offers/" + ++numberPage).get();
        }
        return list;
    }

    public void work() throws IOException, ParseException {
        dbWork.createNewTables();
        if (this.dbWork.getLastLaunch().get(Calendar.YEAR) == 1970) {
            System.out.println(this.scanHtmlPerYear());
            log.info("Добавлены вакансии с начала года.");
        } else {
            System.out.println(this.scanHtmlPerDay());
            log.info("Добавлены вакансии за последний день.");
        }
        dbWork.setLastLaunch();
    }

    public static void main(String[] args) {
        HtmlParse h = new HtmlParse();
        h.start();
    }
}



