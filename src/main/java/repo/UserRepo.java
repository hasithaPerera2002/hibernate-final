package repo;

import entity.User;
import org.hibernate.Session;

public interface UserRepo extends SuperRepo<User,String>{
    User getUser(String user, Session session);

    String getLAstId(Session session);
}
