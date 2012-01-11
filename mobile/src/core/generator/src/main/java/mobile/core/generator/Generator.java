package mobile.core.generator;

import java.util.ArrayList;
import java.util.List;

import mobile.entity.manager.JPManager;
import mobile.entity.manager.JPManagerFactory;

public class Generator {

	private final static String OUTPUT_FOLDER = "/home/ronald/TesisOutput";
	// private final static String OUTPUT_FOLDER = "C:/Users/diogonal/Desktop/TesisOutput";

	/**
	 * Generates two scripts (drop, create) for all tables
	 **/
	private static void generateSqlScriptsForAllTables() throws Exception {
		SqlGenerator sqlGenerator = new SqlGenerator(OUTPUT_FOLDER);
		sqlGenerator.generateAllTables();
	}

	/**
	 * Generates two scripts (drop, create) for a list of tables
	 **/
	private static void generateSqlScriptsForListOfTables(List<String> ltables)
			throws Exception {
		SqlGenerator sqlGenerator = new SqlGenerator(OUTPUT_FOLDER);
		sqlGenerator.generate(ltables);
	}

	/**
	 * Generates file classes for all entities
	 * 
	 * @throws Exception
	 **/
	private static void generateClasesForAllEntities() throws Exception {
		String outputUpperPackage = "mobile.entity";
		EntityGenerator entityGenerator = new EntityGenerator(OUTPUT_FOLDER,
				outputUpperPackage);
		entityGenerator.generateAllEntities();
	}

	/**
	 * Generates file classes for a list of entities
	 * 
	 * @throws Exception
	 */
	private static void generateClasesForListOfTables(List<String> lTables)
			throws Exception {
		String outputUpperPackage = "mobile.entity";
		EntityGenerator entityGenerator = new EntityGenerator(OUTPUT_FOLDER,
				outputUpperPackage);
		for (String table : lTables) {
			entityGenerator.generateOneEntity(table);
		}
	}

	/**
	 * Main
	 */
	public static void main(String[] args) {
		try {
			// Initialize Jpa
			JPManagerFactory.createEntityManagerFactory("generator");
			JPManager.createEntityManager();

			// ************************************************
			// Generators for all tables
			// ************************************************
			// generateSqlScriptsForAllTables();
			// generateClasesForAllEntities();

			// ************************************************
			// Generators for a list of tables
			// ************************************************
			List<String> ltables = new ArrayList<String>();
			ltables.add("QUOTA_TYPE");
			ltables.add("SOLICITUDE");
			generateSqlScriptsForListOfTables(ltables);
			generateClasesForListOfTables(ltables);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPManager.close();
			JPManagerFactory.close();
		}
	}
}