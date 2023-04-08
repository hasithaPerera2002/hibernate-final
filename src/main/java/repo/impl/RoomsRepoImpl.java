package repo.impl;

import entity.Rooms;
import org.hibernate.Session;
import repo.RoomsRepo;

public class RoomsRepoImpl implements RoomsRepo {
    @Override
    public void add(Rooms obj, Session session) {
        session.save(obj);
    }

    @Override
    public void update(Rooms obj, Session session) {
        session.update(obj);
    }

    @Override
    public void delete(Rooms obj, Session session) {
    session.delete(obj);
    }

    @Override
    public String getLastId(Session session) {
      return  (String) session.createSQLQuery("SELECT id from  rooms ORDER BY id DESC LIMIT 1").getSingleResult();
    }
}

