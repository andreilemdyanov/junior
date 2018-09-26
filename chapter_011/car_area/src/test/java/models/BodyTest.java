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
 * Class BodyTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 22.08.2018
 */
public class BodyTest {
    private SessionFactory factory;

    public BodyTest() {
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
        Body body = Body.builder().id(1).type("sedan").build();
        List<Body> list = session.createQuery("select b from Body b join fetch b.cars").getResultList();
        assertThat(body, is(list.get(0)));
        session.close();
        factory.close();
    }

    @Test
    public void whenUpdateEntityThenChangeIt() {
        Session session = factory.openSession();
        session.beginTransaction();
        Body body = Body.builder().id(1).type("cabriolet").build();
        session.update(body);
        session.getTransaction().commit();
        assertThat(session.get(Body.class, 1), is(body));
        session.close();
        factory.close();
    }

    @Test
    public void whenDeleteEntityThenRemoveIt() {
        Session session = factory.openSession();
        session.beginTransaction();
        Body body = Body.builder().id(1).build();
        session.delete(body);
        session.getTransaction().commit();
        assertThat(session.get(Body.class, 1), is(nullValue()));
        session.close();
        factory.close();
    }

}