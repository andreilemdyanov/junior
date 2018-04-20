package jsp;

import java.util.Calendar;

/**
 * Class User.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 21.04.2018
 */
public class User {
    private String name;
    private String login;
    private String email;
    private Calendar createDate;

    public User() {
    }

    public User(String name, String login, String email, Calendar createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='"
                + name
                + '\''
                + ", login='"
                + login
                + '\''
                + ", email='"
                + email
                + '\''
                + ", createDate="
                + createDate.getTime()
                + '}';
    }
}
