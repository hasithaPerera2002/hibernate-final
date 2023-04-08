package service;

import DTO.RoomsDTO;

public interface RoomsService extends SuperService<RoomsDTO,String> {
    String getNewID();
}
