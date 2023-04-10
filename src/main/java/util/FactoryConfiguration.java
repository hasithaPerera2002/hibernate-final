package util;

import entity.RoomTypes;
import entity.Rooms;
import entity.Student;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private final SessionFactory sessionFactory;
    private FactoryConfiguration() {
        Configuration configuration=new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Rooms.class)
                .addAnnotatedClass(RoomTypes.class).addAnnotatedClass(User.class);
        sessionFactory= configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        if (factoryConfiguration == null) {
            factoryConfiguration = new FactoryConfiguration();
        }
        return factoryConfiguration;
    }
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
