package service.impl;

import DTO.RoomsDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repo.RepoFactory;
import repo.RepoTypes;
import repo.RoomsRepo;
import service.RoomsService;
import util.Converter;
import util.FactoryConfiguration;

import javax.persistence.NoResultException;

public class RoomsServiceImpl implements RoomsService {
    RoomsRepo roomsRepo= RepoFactory.getInstance().getRepo(RepoTypes.ROOMS_REPO);
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
        return 0;
    }

    @Override
    public int update(RoomsDTO obj) {
        return 0;
    }

    @Override
    public String getNewID() {
       String id= lastId();
        if (id != null) {
            String [] split=id.split("R");
            int i = Integer.parseInt(split[1]);
            return String.format("R%3d",++i);
        }else {
            return "R001";
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
