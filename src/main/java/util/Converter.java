package util;

import DTO.RoomsDTO;
import DTO.StudentDTO;
import DTO.UserDTO;
import entity.RoomTypes;
import entity.Rooms;
import entity.Student;
import entity.User;

import java.sql.Date;
import java.time.LocalDate;

public class Converter {

    private static Converter converter;

    public static Converter getConverter( ){
        if (converter == null) {
            converter=new Converter();
        }
        return converter;
    }
    public UserDTO toUserDTO(User user) {
      return   new UserDTO(user.getId(), user.getFname(), user.getSname(), user.getEmail(), user.getContact(), user.getPassword(), user.getAddress(), user.getNic());
    }
    public User toUser(UserDTO user) {
      return   new User(user.getId(), user.getfName(), user.getsName(), user.getPassword(), user.getAddress(), user.getEmail(), user.getContactNo(), user.getNic());
    }



    public Rooms toRoom(RoomsDTO obj) {
       return new Rooms(obj.getId(),new RoomTypes(obj.getRoomTypeID(),obj.getRoomType(), obj.getKeymoney(), null),null);
    }

    public RoomsDTO toRoomDTO(Rooms r) {
        return new RoomsDTO(r.getId(),r.getRoomType().getRoomId(),r.getRoomType().getRoomType(), r.getRoomType().getKeyMoney());
    }

    public Student toStudent(StudentDTO obj) {
        return new Student(obj.getId(),obj.getFname(),obj.getSname(),obj.getContact(), obj.getAddress(), obj.getNic(), Date.valueOf(obj.getFromDate()),Date.valueOf(obj.getToDate()),Converter.converter.toRoom(obj.getRoomsDTO()) );
    }

    public StudentDTO toStudentDTO(Student student) {
        return new StudentDTO(student.getId(), student.getFname(), student.getSname(), student.getAddress(), student.getContact(), student.getNic(), student.getFromDate().toLocalDate(),student.getToDate().toLocalDate(), Converter.getConverter().toRoomDTO(student.getRooms()));
    }
}
