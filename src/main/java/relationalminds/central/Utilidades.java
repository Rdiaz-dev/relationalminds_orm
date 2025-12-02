package relationalminds.central;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Utilidades {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("RelationalMindsPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        emf.close();
    }
}
