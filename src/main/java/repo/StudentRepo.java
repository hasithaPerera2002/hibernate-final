package repo;

import entity.Student;
import org.hibernate.Session;
import util.Paid;

import java.util.List;

public interface  StudentRepo extends SuperRepo<Student,String> {

    List<Student> getAll(Session session);

    String getLastId(Session session);

    void update  (String id, Paid paid,Session session);

    int getTotalCount(Session session);

    int getUnPaidCount(Session session);
}
