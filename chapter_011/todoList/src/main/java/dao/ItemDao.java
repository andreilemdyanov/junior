package dao;

import models.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;
import java.util.List;
import java.util.function.Function;

/**
 * Class ItemDao.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 21.08.2018
 */
public enum ItemDao {
    INSTANCE;

    private final SessionFactory factory;

    ItemDao() {
        factory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
    }

    public List<Item> getAllItems() {
        return this.tx(session -> session.createQuery("from Item").list());
    }

    public void addItem(String description) {
        this.tx(session -> {
            Item item = Item.builder()
                    .created(new Timestamp(System.currentTimeMillis()))
                    .description(description)
                    .build();
            session.save(item);
            return session;
        });

    }
}
