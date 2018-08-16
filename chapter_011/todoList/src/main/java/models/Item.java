package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Class Item.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 16.08.2018
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private int id;
    private String description;
    private Timestamp created;
    private boolean done;

}
