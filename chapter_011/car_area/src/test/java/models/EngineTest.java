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
 * Class EngineTest.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 26.09.2018
 */
public class EngineTest {
    private SessionFactory factory;

    public EngineTest() {
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
        Engine engine = Engine.builder().id(1).capacity(2000).power(330).type("electro").build();
        List<Engine> list = session.createQuery("select e from Engine e join fetch e.cars").getResultList();
        assertThat(engine, is(list.get(0)));
        session.close();
        factory.close();
    }

    @Test
    public void whenUpdateEntityThenChangeIt() {
        Session session = factory.openSession();
        session.beginTransaction();
        Engine engine = Engine.builder().id(1).capacity(2500).power(400).type("dizel").build();
        session.update(engine);
        session.getTransaction().commit();
        assertThat(session.get(Engine.class, 1), is(engine));
        session.close();
        factory.close();
    }

    @Test
    public void whenDeleteEntityThenRemoveIt() {
        Session session = factory.openSession();
        session.beginTransaction();
        Engine engine = Engine.builder().id(1).build();
        session.delete(engine);
        session.getTransaction().commit();
        assertThat(session.get(Engine.class, 1), is(nullValue()));
        session.close();
        factory.close();
    }


}