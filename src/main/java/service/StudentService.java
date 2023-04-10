package service;

import DTO.StudentDTO;
import javafx.collections.ObservableList;

public interface StudentService  extends SuperService<StudentDTO,String>{
    ObservableList<StudentDTO> getAll();

    String getNewId();
}
