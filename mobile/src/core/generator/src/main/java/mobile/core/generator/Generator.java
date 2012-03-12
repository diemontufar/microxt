package mobile.core.generator;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import mobile.entity.manager.JPManager;
import mobile.entity.manager.JPManagerFactory;
import mobile.tools.common.Log;

import com.csvreader.CsvReader;

public class Generator {

	private final static String OUTPUT_FOLDER = "/home/ronald/TesisOutput";

	// private final static String OUTPUT_FOLDER =
	// "C:/Users/diogonal/Desktop/TesisOutput";
	
	// Logger
	private final static Logger log = Log.getInstance();

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
	private static void generateSqlScriptsForListOfTables(List<String> ltables) throws Exception {
		SqlGenerator sqlGenerator = new SqlGenerator(OUTPUT_FOLDER);
		sqlGenerator.generate(ltables);
	}

	/**
	 * Generates file classes for all entities
	 * 
	 * @throws Exception
	 **/
	private static void generateClasesForAllEntities() throws Exception {
		EntityGenerator entityGenerator = new EntityGenerator(OUTPUT_FOLDER);
		entityGenerator.generateAllEntities();
	}

	/**
	 * Generates file classes for a list of entities
	 * 
	 * @throws Exception
	 */
	private static void generateClasesForListOfTables(List<String> lTables) throws Exception {
		EntityGenerator entityGenerator = new EntityGenerator(OUTPUT_FOLDER);
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
			generateSqlScriptsForAllTables();
			// generateClasesForAllEntities();

			// ************************************************
			// Generators for a list of tables
			// ************************************************
//			 List<String> ltables = new ArrayList<String>();
//			 ltables.add("PROCESS_COMPONENT");
//			 generateSqlScriptsForListOfTables(ltables);
//			 generateClasesForListOfTables(ltables);

			// ************************************************
			// Generators for a list of tables taken from entity.csv
			// ************************************************
//			 final String CSV_FILE = "/home/ronald/Escritorio/entity.csv";
//			 List<String> ltables = new ArrayList<String>();
//			 ltables = readTablesFromCsv(CSV_FILE);
//			 generateSqlScriptsForListOfTables(ltables);
//			 generateClasesForListOfTables(ltables);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPManager.close();
			JPManagerFactory.close();
		}
	}

	private static List<String> readTablesFromCsv(String csvFile) {
		CsvReader csvReader = null;
		List<String> ltable = new ArrayList<String>();

		try {
			// CSV reader
			File fichero = new File(csvFile);
			FileReader freader = new FileReader(fichero);
			csvReader = new CsvReader(freader);

			// Header
			csvReader.readHeaders();

			// Read entities
			log.info("Entidades");
			while (csvReader.readRecord()) {
				String tableId = csvReader.get("TABLE_ID");
				log.info(tableId);
				ltable.add(tableId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (csvReader != null) {
				csvReader.close();
			}
		}
		return ltable;
	}
}