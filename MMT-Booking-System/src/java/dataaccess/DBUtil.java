package dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("MMTPU");
    
    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}
