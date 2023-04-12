package service.impl;

import DTO.UserDTO;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repo.RepoFactory;
import repo.RepoTypes;
import repo.UserRepo;
import service.UserService;
import util.Converter;
import util.FactoryConfiguration;

import javax.persistence.NoResultException;

public class UserServiceImpl implements UserService {

   private UserRepo userRepo= RepoFactory.getInstance().getRepo(RepoTypes.USER_REPO);

    @Override
    public UserDTO getUser(String text) {
        Session session= FactoryConfiguration.getInstance().getSession();
        try{
            User user = userRepo.getUser(text, session);
            return Converter.getConverter().toUserDTO(user);
        }catch (NullPointerException | NoResultException e){
            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }

    }

    @Override
    public int add(UserDTO obj) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
        try{
            userRepo.add(Converter.getConverter().toUser(obj), session);
            System.out.println(obj+"service");
            transaction.commit();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return 0;
        }finally {
            session.close();
        }
    }

    @Override
    public String getNewID() {
        String lastID = getLastID();
        if (lastID == null) {
            return "U001";
        }else {
            String [] split=lastID.split("U");
            int i = Integer.parseInt(split[1]);
            return String.format("U%03d",++i);

        }

    }

    @Override
    public String getLastID() {
        Session session= FactoryConfiguration.getInstance().getSession();

        try{
           return userRepo.getLAstId(session);

        }catch (NullPointerException | NoResultException e){
            e.printStackTrace();


        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public int delete(UserDTO obj) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
        try{
            userRepo.delete(Converter.getConverter().toUser(obj), session);
            transaction.commit();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return 0;
        }finally {
            session.close();
        }
    }

    @Override
    public int update(UserDTO obj) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
        try{
            System.out.println(obj.getId()+" updated");
            userRepo.update(Converter.getConverter().toUser(obj), session);
            transaction.commit();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
            return 0;
        }finally {
            session.close();
        }
    }
}
