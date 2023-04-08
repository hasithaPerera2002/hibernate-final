package service;

import DTO.UserDTO;
import entity.User;

public interface UserService extends SuperService<UserDTO,String> {
    UserDTO getUser(String text);



    String getNewID();
    String getLastID();
}
