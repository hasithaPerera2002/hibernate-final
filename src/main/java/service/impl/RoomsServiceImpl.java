package service.impl;

import DTO.RoomTypesDTO;
import DTO.RoomsDTO;
import DTO.RoomsStuDTO;
import entity.RoomTypes;
import entity.Rooms;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repo.RepoFactory;
import repo.RepoTypes;
import repo.RoomsRepo;
import service.RoomsService;
import util.Converter;
import util.FactoryConfiguration;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class RoomsServiceImpl implements RoomsService {
   private RoomsRepo roomsRepo= RepoFactory.getInstance().getRepo(RepoTypes.ROOMS_REPO);
    @Override
    public int add(RoomsDTO obj) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
        try{
          roomsRepo.add(Converter.getConverter().toRoom(obj),session);
            transaction.commit();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }finally {
            session.close();
        }
    }

    @Override
    public int delete(RoomsDTO obj) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
        try{
            roomsRepo.delete(Converter.getConverter().toRoom(obj),session);
            transaction.commit();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }finally {
            session.close();
        }
    }

    @Override
    public int update(RoomsDTO obj) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
        try{
            roomsRepo.update(Converter.getConverter().toRoom(obj),session);
            transaction.commit();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }finally {
            session.close();
        }
    }

    @Override
    public String getNewID() {
       String id= lastId();
        if (id != null) {
            String [] split=id.split("R");
            int i = Integer.parseInt(split[1]);
            return String.format("R%03d",++i);
        }else {
            return "R001";
        }
    }

    @Override
    public ObservableList<RoomsDTO> getAllRooms() {
        Session session= FactoryConfiguration.getInstance().getSession();
        ObservableList<RoomsDTO> result = FXCollections.observableArrayList();
      try  {
          List<Rooms> list= roomsRepo.getAll(session);

          for (Rooms r:list
               ) {
              result.add(Converter.getConverter().toRoomDTO(r));
          }
          return result;
        }catch (NullPointerException |NoResultException e) {
          e.printStackTrace();
          return null;
      }finally {
          session.close();
      }
    }

    @Override
    public ObservableList<RoomsDTO> getAllRoomsWithoutStudents() {
        Session session= FactoryConfiguration.getInstance().getSession();
        ObservableList<RoomsDTO> result = FXCollections.observableArrayList();
        try  {
            List<Rooms> list= roomsRepo.getAllWithoutStudents(session);

            for (Rooms r:list
            ) {
                if (r.getStudent() == null) {
                    result.add(Converter.getConverter().toRoomDTO(r));
                }

            }
            return result;
        }catch (NullPointerException |NoResultException e) {
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public ObservableList<RoomTypesDTO> getRoomTypes() {
        Session session =FactoryConfiguration.getInstance().getSession();
        ObservableList<RoomTypesDTO> result = FXCollections.observableArrayList();
        try{
            List<RoomTypes> roomTypes = roomsRepo.getRoomTypes(session);

            for (RoomTypes r :
                    roomTypes) {
                result.add(new RoomTypesDTO(r.getRoomId(), r.getRoomType(), r.getKeyMoney()));

            }
            return result;
        }catch (NullPointerException |NoResultException e) {
        e.printStackTrace();
        return result;
        }  finally{
            session.close();
        }
    }

    @Override
    public boolean update(RoomsStuDTO roomsStuDTO) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
        try{
            roomsRepo.update(new Rooms(roomsStuDTO.getRoomsDTO().getId(),new RoomTypes(roomsStuDTO.getRoomsDTO().getRoomTypeID(),
                    roomsStuDTO.getRoomsDTO().getRoomType(),roomsStuDTO.getRoomsDTO().getKeymoney(),null),
                    Converter.getConverter().toStudent(roomsStuDTO.getStudentsDTO())),session);
            transaction.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    private String lastId() {
        Session session= FactoryConfiguration.getInstance().getSession();
        try{
            return roomsRepo.getLastId(session);
        }catch(NullPointerException | NoResultException exception){
            exception.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }
}
