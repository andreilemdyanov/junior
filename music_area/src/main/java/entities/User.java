package entities;

import lombok.Builder;
import lombok.Value;

import java.util.Calendar;

/**
 * Class User.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
@Builder
@Value
public class User {

    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Calendar timeCreate;
    private int roleId;

}
