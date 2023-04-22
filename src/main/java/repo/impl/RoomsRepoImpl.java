package repo.impl;

import entity.RoomTypes;
import entity.Rooms;
import entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repo.RoomsRepo;

import java.util.List;

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

    @Override
    public List<Rooms> getAll(Session session) {
        return session.createCriteria(Rooms.class).list();
    }

    @Override
    public List<RoomTypes> getRoomTypes(Session session) {
        return session.createCriteria(RoomTypes.class).list();
    }

    @Override
    public List<Rooms> getAllWithoutStudents(Session session) {
        return session.createCriteria(Rooms.class).list();
    }

    @Override
    public int getTotalCount(Session session) {
        Long singleResult = (Long) session.createQuery(" select count (*) from Rooms ").getSingleResult();
       return singleResult.intValue();
    }

    @Override
    public List<Rooms> getSearchList(Session session, String id) {
        //Query qry = session.createQuery("From Rooms as rb where rb.id  like `?`");
       // List<Rooms> resultList = (List<Rooms>) session.createSQLQuery("SELECT * from rooms as rb where rb.id like '"%"'+'"id"'+'"%"'").getResultList();

        return null;
        //qry.setString(0, "%" + id + "%");
       // return  (List<Rooms>) qry.getResultList();
    }


}

