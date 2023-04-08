package service;

import service.impl.RoomsServiceImpl;
import service.impl.UserServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;
    UserService userService;
    RoomsService roomsService;
    private ServiceFactory() {
        userService=new UserServiceImpl();
        roomsService=new RoomsServiceImpl();
    }
    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
            serviceFactory  = new ServiceFactory();
        }
        return serviceFactory;
    }
    public  <T>T getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case USER_SERVICE:
                return (T) userService;
            case ROOM_SERVICE:
                return (T) roomsService;

        }
        return null;
    }

}
