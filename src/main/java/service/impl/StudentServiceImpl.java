package service.impl;

import DTO.StudentDTO;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.SQLGrammarException;
import repo.RepoFactory;
import repo.RepoTypes;
import repo.StudentRepo;
import service.StudentService;
import util.Converter;
import util.FactoryConfiguration;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private StudentRepo studentRepo= RepoFactory.getInstance().getRepo(RepoTypes.STUDENT_REPO);
    @Override
    public int add(StudentDTO obj) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
       try {
            studentRepo.add(Converter.getConverter().toStudent(obj), session);
            transaction.commit();
           System.out.println("done==========");
            return 1;
        }catch (Exception e) {
           e.printStackTrace();
           transaction.rollback();
           return 0;
       }finally {
           session.close();
       }
    }

    @Override
    public int delete(StudentDTO obj) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
        try {
            studentRepo.delete(Converter.getConverter().toStudent(obj), session);
            transaction.commit();
            return 1;
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return 0;
        }finally {
            session.close();
        }
    }

    @Override
    public int update(StudentDTO obj) {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction= session.beginTransaction();
        try {
            studentRepo.update(Converter.getConverter().toStudent(obj), session);
            transaction.commit();

            return 1;
        }catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return 0;
        }finally {
            session.close();
        }
    }
    public ObservableList<StudentDTO> getAll(){
        Session session= FactoryConfiguration.getInstance().getSession();
        ObservableList<StudentDTO> observableList=FXCollections.observableArrayList();
       try {
           List<Student> all = studentRepo.getAll(session);
           for(Student student:all){
               observableList.add(Converter.getConverter().toStudentDTO(student));
           }
           return observableList;
       }catch(NullPointerException | NoResultException e){
           e.printStackTrace();
           return observableList;
       }finally {
           session.close();
       }
    }
    public String getLastId() {
        Session session= FactoryConfiguration.getInstance().getSession();
       try {
           return studentRepo.getLastId(session);
        }catch (NullPointerException | NoResultException| SQLGrammarException  e) {
           e.printStackTrace();
           return null;
       }finally {
           session.close();
       }
    }
    @Override
    public String getNewId() {

       String id = getLastId();
       if(id==null){
           return "S001";
       }else{
           String[] split = id.split("S");
           int i = Integer.parseInt(split[1]);
           return String.format("S%03d", ++i);
       }
    }
}
