package jsp;

import lombok.Builder;
import lombok.Value;

import java.util.Calendar;

/**
 * Class User.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 16.05.2018
 */
@Builder
@Value
public class User {

    private int id;
    private String name;
    private String login;
    private String password;
    private String email;
    private Calendar createDate;
    private Role role;
    private String country;
    private String city;

}

