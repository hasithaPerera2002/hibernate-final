package service;

import DTO.StudentDTO;
import DTO.tableDTO.StudentTblDTO;
import javafx.collections.ObservableList;
import util.Paid;

public interface StudentService  extends SuperService<StudentDTO,String>{
    ObservableList<StudentDTO> getAll();

    String getNewId();



    boolean update(String id, Paid paid);

    int getTotalCount();

    int getUnPaidCount();
}
