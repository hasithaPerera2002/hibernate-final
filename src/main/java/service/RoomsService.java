package service;

import DTO.RoomTypesDTO;
import DTO.RoomsDTO;
import DTO.RoomsStuDTO;
import javafx.collections.ObservableList;

import java.util.List;

public interface RoomsService extends SuperService<RoomsDTO,String> {
    String getNewID();

    ObservableList<RoomsDTO> getAllRooms();
    ObservableList<RoomsDTO> getAllRoomsWithoutStudents();

    ObservableList<RoomTypesDTO> getRoomTypes();

    boolean update(RoomsStuDTO roomsStuDTO);

    int getTotalCount();
}
