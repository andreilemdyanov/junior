package ru.job4j.jdbc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Entries.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 23.12.2017
 */
@XmlRootElement
public class Entries {

    private List<Entry> list;

    public List<Entry> getList() {
        return list;
    }

    @XmlElement(name = "entry")
    public void setList(List<Entry> list) {
        this.list = list;
    }

    public void add(Entry entry) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        list.add(entry);
    }
}
