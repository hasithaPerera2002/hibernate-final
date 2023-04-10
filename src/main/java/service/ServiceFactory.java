package service;

import service.impl.RoomsServiceImpl;
import service.impl.StudentServiceImpl;
import service.impl.UserServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;
   private UserService userService;
   private RoomsService roomsService;
   private StudentService studentsService;
    private ServiceFactory() {
        userService=new UserServiceImpl();
        roomsService=new RoomsServiceImpl();
        studentsService=new StudentServiceImpl();
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
            case STUDENT_SERVICE:
                return (T) studentsService;


        }
        return null;
    }

}
