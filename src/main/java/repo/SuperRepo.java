package repo;

import org.hibernate.Session;

public interface SuperRepo<T,String> {
    void add(T obj, Session session);
    void update(T obj,Session session);
    void delete(T obj,Session session);
}
