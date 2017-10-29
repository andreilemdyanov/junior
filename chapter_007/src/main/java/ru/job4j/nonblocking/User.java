package ru.job4j.nonblocking;

/**
 * Class User.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 29.10.2017
 */
public class User {

    private String name;
    private int version;

    public String getName() {
        return name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    User(String name) {
        this.name = name;
        version = 0;
    }
}
