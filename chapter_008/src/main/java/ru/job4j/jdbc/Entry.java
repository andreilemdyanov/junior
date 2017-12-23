package ru.job4j.jdbc;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class Entry.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 23.12.2017
 */
@XmlRootElement
public class Entry {

    private int fieldM;

    public Entry() {
    }

    public int getField() {
        return fieldM;
    }

    @XmlElement
    public void setField(int field) {
        this.fieldM = field;
    }
}
