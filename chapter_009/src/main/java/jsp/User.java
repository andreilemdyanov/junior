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

    private int id;
    private String name;
    private String login;
    private String password;
    private String email;
    private Calendar createDate;
    private Role role;

    public User() {
    }

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

