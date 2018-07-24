package entities;

import lombok.Builder;
import lombok.Value;

/**
 * Class MusicType.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
@Builder
@Value
public class MusicType {

    private int id;
    private String name;

}
