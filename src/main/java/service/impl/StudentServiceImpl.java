package service.impl;

import DTO.RoomsDTO;
import DTO.StudentDTO;
import DTO.tableDTO.StudentTblDTO;
import entity.RoomTypes;
import entity.Rooms;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.SQLGrammarException;
import repo.RepoFactory;
import repo.RepoTypes;
import repo.RoomsRepo;
import repo.StudentRepo;
import service.StudentService;
import util.Converter;
import util.FactoryConfiguration;
import util.Paid;
import util.RoomType;

import javax.persistence.NoResultException;
import java.util.HashMap;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentRepo studentRepo = RepoFactory.getInstance().getRepo(RepoTypes.STUDENT_REPO);
    private RoomsRepo roomsRepo=RepoFactory.getInstance().getRepo(RepoTypes.ROOMS_REPO);

    @Override
    public int add(StudentDTO obj) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentRepo.add(Converter.getConverter().toStudent(obj), session);
            transaction.commit();

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return 0;
        } finally {
            session.close();
        }
    }

    @Override
    public int delete(StudentDTO student) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            RoomsDTO obj = student.getRoomsDTO();
            roomsRepo.update(new Rooms(obj.getId(),new RoomTypes(obj.getRoomTypeID(),obj.getRoomType(), obj.getKeymoney(), null),null),session);
            studentRepo.delete(Converter.getConverter().toStudent(student), session);
            System.out.println(obj.getId()+"=========");
            transaction.commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return 0;
        } finally {
            session.close();
        }
    }

    @Override
    public int update(StudentDTO obj) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            studentRepo.update(Converter.getConverter().toStudent(obj), session);
            transaction.commit();

            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return 0;
        } finally {
            session.close();
        }
    }

    public ObservableList<StudentDTO> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        ObservableList<StudentDTO> observableList = FXCollections.observableArrayList();
        try {
            List<Student> all = studentRepo.getAll(session);
            for (Student student : all) {
                System.out.println(student);
                observableList.add(Converter.getConverter().toStudentDTO(student));
            }
            return observableList;
        } catch (NullPointerException | NoResultException e) {
            e.printStackTrace();
            return observableList;
        } finally {
            session.close();
        }
    }

    public String getLastId() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            return studentRepo.getLastId(session);
        } catch (NullPointerException | NoResultException | SQLGrammarException e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public String getNewId() {

        String id = getLastId();
        if (id == null) {
            return "S001";
        } else {
            String[] split = id.split("S");
            int i = Integer.parseInt(split[1]);
            return String.format("S%03d", ++i);
        }
    }

    @Override
    public HashMap<String, Double> getMonthlyIncome() {
        Session session=FactoryConfiguration.getInstance().getSession();
        try{
            List income=studentRepo.getIncome(session);
            return Converter.getConverter().toHashMap(income);
        }catch (Exception e){

            e.printStackTrace();
            return null;
        }finally {
            session.close();
        }
    }


    @Override
    public boolean update(String id, Paid paid) {
        Session session=FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
        try{
            studentRepo.update(id,paid,session);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public int getTotalCount() {
        Session session=FactoryConfiguration.getInstance().getSession();
        try{
            return studentRepo.getTotalCount(session);
        }catch(NullPointerException | NoResultException e){
            e.printStackTrace();
            return 0;
        }finally {
            session.close();
        }
    }

    @Override
    public int getUnPaidCount() {
        Session session=FactoryConfiguration.getInstance().getSession();
        try{
           return studentRepo.getUnPaidCount(session);
        }catch (NullPointerException | NoResultException e){
            e.printStackTrace();
            return 0;
        }
    }
}
