package repo.impl;

import entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repo.UserRepo;

public class UserRepoImpl implements UserRepo {
    @Override
    public void add(User obj, Session session) {
        System.out.println(obj);
        session.save(obj);
    }

    @Override
    public void update(User obj, Session session) {
        session.update(obj);
    }

    @Override
    public void delete(User obj, Session session) {
        session.delete(obj);
    }

    @Override
    public User getUser(String text, Session session) {
        Query query = session.createQuery("from User where id= :id");
        query.setString("id",text );
        return  (User) query.uniqueResult();

    }

    @Override
    public String getLAstId(Session session) {
        return (String) session.createSQLQuery("SELECT id from  user ORDER BY id DESC LIMIT 1").getSingleResult();

    }
}
