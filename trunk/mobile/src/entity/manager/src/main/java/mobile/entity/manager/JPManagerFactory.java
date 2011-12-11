package mobile.entity.manager;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import mobile.tools.common.Log;
import mobile.tools.common.convertion.FormatDates;

import org.apache.log4j.Logger;

public final class JPManagerFactory {

	private static JPManagerFactory INSTANCE = null;

	private EntityManagerFactory emf = null;

	private final static String PERSISTENCE_UNIT = "central";

	private final static Logger log = Log.getInstance();

	private JPManagerFactory() {
		// Avoid creating the entity manager here, because the close method
		// problem
	}

	public static void createEntityManagerFactory() {
		createEntityManagerFactory(PERSISTENCE_UNIT);
	}

	public static void createEntityManagerFactory(final String persistenceUnit) {
		Long start = System.currentTimeMillis(); // Start
		getInstance().emf = Persistence
				.createEntityManagerFactory(persistenceUnit);
		log.info("Load time for persistence: " 
				+ FormatDates.getInstance().getTimeCountFormat()
                .format(new Date(System.currentTimeMillis() - start)) );
	}

	// creador sincronizado para protegerse de posibles problemas multi-hilo
	// otra prueba para evitar instanciación múltiple
	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new JPManagerFactory();
		}
	}

	private static JPManagerFactory getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}
		return INSTANCE;
	}

	public static EntityManager createEntityManager() {
		return getInstance().emf.createEntityManager();
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return getInstance().emf;
	}

	public static void close() {
		if (getInstance().emf != null) {
			getInstance().emf.close();
		}
	}
}
