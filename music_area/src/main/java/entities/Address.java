package entities;

import lombok.Builder;
import lombok.Value;

/**
 * Class Address.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
@Builder
@Value
public class Address {

    private int id;
    private String country;
    private String city;
    private String street;
    private int userId;

}
