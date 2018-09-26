package models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Class GearboxTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 26.09.2018
 */
public class GearboxTest {
    private SessionFactory factory;

    public GearboxTest() {
        factory = new Configuration()
                .configure()
                .buildSessionFactory();
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        Body body = Body.builder().type("sedan").build();
        Engine engine = Engine.builder().capacity(2000).power(330).type("electro").build();
        Gearbox gearbox = Gearbox.builder().type("auto").build();
        Car car = Car.builder().body(body).engine(engine).gearbox(gearbox).price(20000).year(1997).build();
        session.save(car);
        tx.commit();
        session.close();
    }

    @Test
    public void whenSelectEntityThenGetIt() {
        Session session = factory.openSession();
        Gearbox gearbox = Gearbox.builder().id(1).type("auto").build();
        List<Gearbox> list = session.createQuery("select g from Gearbox g join fetch g.cars").getResultList();
        assertThat(gearbox, is(list.get(0)));
        session.close();
        factory.close();
    }

    @Test
    public void whenUpdateEntityThenChangeIt() {
        Session session = factory.openSession();
        session.beginTransaction();
        Gearbox gearbox = Gearbox.builder().id(1).type("manual").build();
        session.update(gearbox);
        session.getTransaction().commit();
        assertThat(session.get(Gearbox.class, 1), is(gearbox));
        session.close();
        factory.close();
    }

    @Test
    public void whenDeleteEntityThenRemoveIt() {
        Session session = factory.openSession();
        session.beginTransaction();
        Gearbox gearbox = Gearbox.builder().id(1).build();
        session.delete(gearbox);
        session.getTransaction().commit();
        assertThat(session.get(Gearbox.class, 1), is(nullValue()));
        session.close();
        factory.close();
    }


}