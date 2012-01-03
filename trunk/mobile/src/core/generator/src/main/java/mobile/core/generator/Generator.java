package mobile.core.generator;

import java.util.ArrayList;
import java.util.List;

import mobile.entity.manager.JPManager;
import mobile.entity.manager.JPManagerFactory;

public class Generator {

	/**
	 * Generate two scripts (drop, create) for all tables
	 **/
	private static void generateSqlScriptsForAllTables() throws Exception {
		SqlGenerator sqlGenerator = new SqlGenerator("/home/ronald/TesisOutput");
		sqlGenerator.generateAllTables();
	}

	/**
	 * Generate two scripts (drop, create) for a list of tables
	 **/
	private static void generateSqlScriptsForListOfTables(List<String> ltables)
			throws Exception {
		SqlGenerator sqlGenerator = new SqlGenerator("/home/ronald/TesisOutput");
		sqlGenerator.generate(ltables);
	}

	/**
	 * Generate file classes for all entities
	 * 
	 * @throws Exception
	 **/
	private static void generateClasesForAllEntities() throws Exception {
		// String outputFolder = "C:/Users/Ronald/Tesis";
		String outputFolder = "/home/ronald/TesisOutput";
		String outputUpperPackage = "mobile.entity";
		EntityGenerator entityGenerator = new EntityGenerator(outputFolder,
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
		// String outputFolder = "C:/Users/Ronald/Tesis";
		String outputFolder = "/home/ronald/TesisOutput";
		String outputUpperPackage = "mobile.entity";
		EntityGenerator entityGenerator = new EntityGenerator(outputFolder,
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

			//************************************************
			// Generators for all tables
			//************************************************
			//generateSqlScriptsForAllTables();
			generateClasesForAllEntities();

			//************************************************
			// Generators for a list of tables
			//************************************************
//			List<String> ltables = new ArrayList<String>();
//			ltables.add("ENTITY_FIELD");
//			generateSqlScriptsForListOfTables(ltables);
//			generateClasesForListOfTables(ltables);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPManager.close();
			JPManagerFactory.close();
		}

	}

	private static void generateSQLScriptsForListOfTables(List<String> lTables) {
		// TODO Auto-generated method stub

	}

}
