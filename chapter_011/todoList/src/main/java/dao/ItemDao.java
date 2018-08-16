package dao;

import models.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;
import java.util.List;

/**
 * Class ItemDao.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 16.08.2018
 */
public class ItemDao {
    public List<Item> getAllItems() {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.getTransaction().begin();
        List<Item> items = session.createQuery("from Item").list();
        session.getTransaction().commit();
        session.close();
        factory.close();
        return items;
    }

    public void addItem(String description) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        Item item = Item.builder()
                .created(new Timestamp(System.currentTimeMillis()))
                .description(description)
                .build();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        factory.close();
    }
}
