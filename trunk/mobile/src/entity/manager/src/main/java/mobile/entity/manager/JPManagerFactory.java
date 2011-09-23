package mobile.entity.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPManagerFactory {
	public static JPManagerFactory INSTANCE = null;
	private EntityManagerFactory emf = null;
	private final static String PERSISTENCE_UNIT = "central";

	private JPManagerFactory(){
		// Avoid creating the entity manager here, because the close method problem
		//emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
	}
	
	public static void createEntityManagerFactory(){
		getInstance().emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
	}
	
	public static void createEntityManagerFactory(final String persistenceUnit){
		getInstance().emf = Persistence.createEntityManagerFactory(persistenceUnit);
	}

	// creador sincronizado para protegerse de posibles problemas multi-hilo
	// otra prueba para evitar instanciación múltiple
	private synchronized static void createInstance(){
		if (INSTANCE == null) {
			INSTANCE = new JPManagerFactory();
		}
	}

	private static JPManagerFactory getInstance(){
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}
	
	public static EntityManager createEntityManager(){
		return getInstance().emf.createEntityManager();
	}
	
	public static void close(){
		if (getInstance().emf != null) {
			getInstance().emf.close();
		}
	}
}
