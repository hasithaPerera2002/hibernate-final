package repo.impl;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repo.StudentRepo;
import util.Paid;

import java.util.List;

public class StudentRepoImpl implements StudentRepo {


    @Override
    public void add(Student obj, Session session) {
        session.save(obj);
    }

    @Override
    public void update(Student obj, Session session) {
        session.update(obj);
    }

    @Override
    public void delete(Student obj, Session session) {
        session.delete(obj);
    }


    @Override
    public List<Student> getAll(Session session) {
        return session.createCriteria(Student.class).list();
    }

    @Override
    public String getLastId(Session session) {
        return (String) session.createSQLQuery("SELECT s_id from  student ORDER BY s_id DESC LIMIT 1").getSingleResult();
    }

    @Override
    public void update(String id, Paid paid, Session session) {
       session.createSQLQuery("UPDATE student SET paid = '" + paid + "'  WHERE s_id = '"+id+"' ");

        System.out.println("==============================================================================");
    }

    @Override
    public int getTotalCount(Session session) {
        Long singleResult = (Long) session.createQuery("SELECT count (*) from Student ").getSingleResult();
        return singleResult.intValue();
    }

    @Override
    public int getUnPaidCount(Session session) {
        Query query = session.createQuery("SELECT COUNT (*) FROM Student WHERE paid = : pay ");
        query.setParameter("pay",Paid.NOT_PAID);
        Long singleResult = (Long) query.getSingleResult();
       return singleResult.intValue();

    }
}
