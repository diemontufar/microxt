package mobile.core.generator;

import java.util.ArrayList;
import java.util.List;

import mobile.entity.manager.JPManager;
import mobile.entity.manager.JPManagerFactory;

public class Generator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Initialize Jpa
			JPManagerFactory.createEntityManagerFactory("generator");
			JPManager.createEntityManager();

			//*******************************************************************
			// Sql Generator - Two files: 1.drop.sql 2.create.sql
			//*******************************************************************
//			SqlGeneratorAllinOneFile sqlGenerator = new SqlGeneratorAllinOneFile(
//					"MYSQL", "/home/ronald/TesisOutput");
//			sqlGenerator.execute();

			//*******************************************************************
			// Entity Generator (ALL) 
			//*******************************************************************
//			// String outputFolder = "C:\\Users\\Ronald\\Tesis";
//			String outputFolder = "/home/ronald/TesisOutput";
//			String outputUpperPackage = "mobile.entity";
//			EntityGenerator entityGenerator = new EntityGenerator(outputFolder,
//					outputUpperPackage);
//			entityGenerator.execute();

			//*******************************************************************
			// Entity Generator (List)
			//*******************************************************************
			List<String> lTables = new ArrayList<String>();
			lTables.add("PROCESS");
			lTables.add("PROFILE");
			// String outputFolder = "C:\\Users\\Ronald\\Tesis";
			String outputFolder = "/home/ronald/TesisOutput";
			String outputUpperPackage = "mobile.entity";
			EntityGenerator entityGenerator = new EntityGenerator(outputFolder,
					outputUpperPackage);
			for (String table : lTables) {
				entityGenerator.generateOneEntity(table);	
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPManager.close();
			JPManagerFactory.close();
		}

	}

}
