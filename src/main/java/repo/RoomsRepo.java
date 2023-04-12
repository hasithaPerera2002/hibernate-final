package repo;

import entity.RoomTypes;
import entity.Rooms;
import org.hibernate.Session;

import java.util.List;

public interface RoomsRepo extends SuperRepo<Rooms,String>{
    String getLastId(Session session);

    List<Rooms> getAll(Session session);

    List<RoomTypes> getRoomTypes(Session session);

    List<Rooms> getAllWithoutStudents(Session session);

    int getTotalCount(Session session);
}
