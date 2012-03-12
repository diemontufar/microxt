package mobile.core.generator;

import mobile.entity.manager.JPManager;
import mobile.entity.manager.JPManagerFactory;
import mobile.tools.common.Log;

import org.apache.log4j.Logger;

public class GeneratorFromCsv {

	private final static String OUTPUT_FOLDER = "/home/ronald/TesisOutput";

	private final static String entityFilePath0 = "/home/ronald/Escritorio/entity0.csv";
	private final static String entityFilePath1 = "/home/ronald/Escritorio/entity1.csv";
	private final static String fieldFilePath0 = "/home/ronald/Escritorio/field0.csv";
	private final static String fieldFilePath1 = "/home/ronald/Escritorio/field1.csv";
	private final static String relationshipFilePath0 = "/home/ronald/Escritorio/relationship0.csv";
	private final static String relationshipFilePath1 = "/home/ronald/Escritorio/relationship1.csv";

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

			// Fill generating entities <Model>
			FillGeneratingEntitiesFromCsv filler = new FillGeneratingEntitiesFromCsv(entityFilePath0, fieldFilePath0,
					relationshipFilePath0);
			filler.fillTables();
			filler.fillFields();
			filler.fillRelationships();

			// Fill generating entities <Microcredit>
			FillGeneratingEntitiesFromCsv filler1 = new FillGeneratingEntitiesFromCsv(entityFilePath1, fieldFilePath1,
					relationshipFilePath1);
			filler1.fillTables();
			filler1.fillFields();
			filler1.fillRelationships();

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
