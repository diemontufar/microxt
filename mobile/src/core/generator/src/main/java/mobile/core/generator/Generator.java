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
		 SqlGeneratorAllinOne sqlGenerator = new SqlGeneratorAllinOne(
		 "MYSQL", "/home/ronald/TesisOutput");
		 sqlGenerator.execute();
	}

	/**
	 * Generate file classes for all entities
	 * @throws Exception 
	 **/
	private static void generateClasesForAllEntities() throws Exception {
		 //String outputFolder = "C:\\Users\\Ronald\\Tesis";
		 String outputFolder = "/home/ronald/TesisOutput";
		 String outputUpperPackage = "mobile.entity";
		 EntityGenerator entityGenerator = new
		 EntityGenerator(outputFolder,
		 outputUpperPackage);
		 entityGenerator.execute();
	}

	/**
	 * Generates file classes for a list of entities
	 * @throws Exception 
	 */
	private static void generateClasesForListOfTables(List<String> lTables) throws Exception {
		// String outputFolder = "C:\\Users\\Ronald\\Tesis";
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

			// Sql generator
			generateSqlScriptsForAllTables();

			// Entity generator
			//generateClasesForAllEntities();

			// List of tables generator
			List<String> lTables = new ArrayList<String>();
			lTables.add("PROCESS");
			lTables.add("PROFILE");
			//generateClasesForListOfTables(lTables);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPManager.close();
			JPManagerFactory.close();
		}

	}

}
