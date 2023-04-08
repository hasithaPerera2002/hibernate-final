package repo;

import entity.Rooms;
import org.hibernate.Session;

public interface RoomsRepo extends SuperRepo<Rooms,String>{
    String getLastId(Session session);
}
