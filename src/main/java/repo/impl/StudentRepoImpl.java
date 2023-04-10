package repo.impl;

import entity.Student;
import org.hibernate.Session;
import repo.StudentRepo;

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
}
