package repo;

import entity.Student;
import org.hibernate.Session;

import java.util.List;

public interface  StudentRepo extends SuperRepo<Student,String> {

    List<Student> getAll(Session session);

    String getLastId(Session session);
}
