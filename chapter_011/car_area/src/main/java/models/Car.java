package models;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * Class Car.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 21.08.2018
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Car {
    @Getter
    @Setter
    int id;
    @Getter
    @Setter
    int year;
    @Getter
    @Setter
    int price;
    @Getter
    @Setter
    Body body;
    @Getter
    @Setter
    Engine engine;
    @Getter
    @Setter
    Gearbox gearbox;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id
                && year == car.year
                && price == car.price
                && Objects.equals(body, car.body)
                && Objects.equals(engine, car.engine)
                && Objects.equals(gearbox, car.gearbox);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, year, price, body, engine, gearbox);
    }
}
