package DTO;

public class RoomsStuDTO {
    private RoomsDTO roomsDTO;
    private StudentDTO studentsDTO;

    public RoomsStuDTO(RoomsDTO roomsDTO, StudentDTO studentsDTO) {
        this.setRoomsDTO(roomsDTO);
        this.setStudentsDTO(studentsDTO);
    }

    public RoomsDTO getRoomsDTO() {
        return roomsDTO;
    }

    public void setRoomsDTO(RoomsDTO roomsDTO) {
        this.roomsDTO = roomsDTO;
    }

    public StudentDTO getStudentsDTO() {
        return studentsDTO;
    }

    public void setStudentsDTO(StudentDTO studentsDTO) {
        this.studentsDTO = studentsDTO;
    }
}
