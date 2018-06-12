package jsp;

import java.util.Calendar;

/**
 * Class User.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 16.05.2018
 */
public class User {

    private final int id;
    private final String name;
    private final String login;
    private final String password;
    private final String email;
    private final Calendar createDate;
    private final Role role;

    public User(int id, String name, String login, String password, String email, Calendar createDate, Role role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.createDate = createDate;
        this.role = role;

    }

    public int getId() {
        return id;
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

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{"
                + "id="
                + id
                + ", name="
                + name
                + ", login="
                + login
                + ", password="
                + password
                + ", email="
                + email
                + ", createDate="
                + createDate.getTime()
                + ", role="
                + role
                + '}';
    }
}

