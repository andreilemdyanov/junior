package models;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class Body.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 21.08.2018
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Body {
    @Getter
    @Setter
    int id;
    @Getter
    @Setter
    String type;
    @Getter
    @Setter
    Set<Car> cars = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Body body = (Body) o;
        return id == body.id
                && Objects.equals(type, body.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
