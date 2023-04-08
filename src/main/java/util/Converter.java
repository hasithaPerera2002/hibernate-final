package util;

import DTO.RoomsDTO;
import DTO.UserDTO;
import entity.RoomTypes;
import entity.Rooms;
import entity.User;

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
}
