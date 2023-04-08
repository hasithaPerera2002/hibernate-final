package repo;

import repo.impl.RoomsRepoImpl;
import repo.impl.StudentRepoImpl;
import repo.impl.UserRepoImpl;

import static java.awt.desktop.UserSessionEvent.Reason.REMOTE;

public class RepoFactory {
    private static  RepoFactory repofactory;
    UserRepo userRepo;
    RoomsRepo roomsRepo;
    StudentRepo studentsRepo;


    private RepoFactory() {
        userRepo=new UserRepoImpl();
        roomsRepo=new RoomsRepoImpl();
        studentsRepo=new StudentRepoImpl();
    }

    public static RepoFactory getInstance() {
        if (repofactory == null) {
            repofactory = new RepoFactory();
        }
        return repofactory;
    }
    public <T> T getRepo(RepoTypes types){
        switch (types){
            case USER_REPO: return (T) userRepo;
            case ROOMS_REPO: return (T) roomsRepo;
            case STUDENT_REPO: return (T) studentsRepo;
            default:return null;
        }
    }
}
