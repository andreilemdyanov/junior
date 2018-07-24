package entities;

import lombok.Builder;
import lombok.Value;

/**
 * Class Role.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
@Builder
@Value
public class Role {

    private int id;
    private String name;

}
