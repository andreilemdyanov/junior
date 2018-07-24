package dao;

import java.util.List;

/**
 * Interface DAOImpl.
 *
 * @author Andrey Lemdyanov {lemdyanov5@mail.ru)
 * @version $Id$
 * @since 24.07.2018
 */
public interface DAOImpl<E> {
    List<E> getAll();
    E  get(int id);
    void create(E e);
    void update(E e);
    void delete(int id);

}
