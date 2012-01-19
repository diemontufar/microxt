package mobile.core.generator;

import org.apache.log4j.Logger;

import mobile.entity.manager.JPManager;
import mobile.entity.manager.JPManagerFactory;
import mobile.tools.common.Log;

public class GeneratorFromCsv {

	private final static String OUTPUT_FOLDER = "/home/ronald/TesisOutput";

	// private final static String OUTPUT_FOLDER =
	// "C:/Users/diogonal/Desktop/TesisOutput";
	
	// Logger
	private final static Logger log = Log.getInstance();


	public static void loadPersistence() throws Exception {
		log.info("Load persistence...");
		JPManagerFactory.createEntityManagerFactory("generator");
		JPManager.createEntityManager();
	}

	public static void closePersistence() throws Exception {
		log.info("Close persistence...");
		JPManager.close();
		JPManagerFactory.close();
	}

	public static void main(String[] args) {

		try {
			
			// Load persistence 
			loadPersistence();
			// Start transaction
			JPManager.beginTransaction();

			// Fill generating entities
			FillGeneratingEntitiesFromCsv filler = new FillGeneratingEntitiesFromCsv();
			filler.fillTables();
			filler.fillFields();
			filler.fillRelationships();

			// Generate sql scripts
			SqlGenerator sqlGenerator = new SqlGenerator(OUTPUT_FOLDER);
			sqlGenerator.generateAllTables();

			// Generate entities
			EntityGenerator entityGenerator = new EntityGenerator(OUTPUT_FOLDER);
			entityGenerator.generateAllEntities();

			// Commit tx and close persistence
			JPManager.commitTransaction();

		} catch (Exception e) {
			e.printStackTrace();
			JPManager.rollbackTransaction();
		} finally {
			try {
				closePersistence();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
