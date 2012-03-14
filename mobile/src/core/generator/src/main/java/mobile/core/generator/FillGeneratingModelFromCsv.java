package mobile.core.generator;

import mobile.entity.manager.JpManager;
import mobile.entity.manager.JPManagerFactory;
import mobile.tools.common.Log;

import org.apache.log4j.Logger;

public class FillGeneratingModelFromCsv {

	private final static String entityFilePath = "/home/ronald/Escritorio/entity0.csv";
	private final static String fieldFilePath = "/home/ronald/Escritorio/field0.csv";
	private final static String relationshipFilePath = "/home/ronald/Escritorio/relationship0.csv";
	private final static String entityFilePath1 = "/home/ronald/Escritorio/entity1.csv";
	private final static String fieldFilePath1 = "/home/ronald/Escritorio/field1.csv";
	private final static String relationshipFilePath1 = "/home/ronald/Escritorio/relationship1.csv";

	// Logger
	private final static Logger log = Log.getInstance();

	public static void loadPersistence() throws Exception {
		log.info("Load persistence...");
		JPManagerFactory.createEntityManagerFactory("generator");
		JpManager.createEntityManager();
	}

	public static void closePersistence() throws Exception {
		log.info("Close persistence...");
		JpManager.close();
		JPManagerFactory.close();
	}

	public static void main(String[] args) {

		try {
			// Load persistence
			loadPersistence();
			// Start transaction
			JpManager.beginTransaction();

			// Fill generating entities <Model>
			FillGeneratingEntitiesFromCsv filler = new FillGeneratingEntitiesFromCsv(entityFilePath, fieldFilePath,
					relationshipFilePath);
			filler.fillTables();
			filler.fillFields();
			filler.fillRelationships();

			// Fill generating entities <Microcredit>
			FillGeneratingEntitiesFromCsv filler1 = new FillGeneratingEntitiesFromCsv(entityFilePath1, fieldFilePath1,
					relationshipFilePath1);
			filler1.fillTables();
			filler1.fillFields();
			filler1.fillRelationships();

			// Commit tx and close persistence
			JpManager.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			JpManager.rollbackTransaction();
		} finally {
			try {
				closePersistence();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
