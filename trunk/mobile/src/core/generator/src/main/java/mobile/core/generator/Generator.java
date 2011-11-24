package mobile.core.generator;

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

			 // Sql Generator - One file per all packages
			 SqlGeneratorAllinOneFile sqlGenerator = new
			 SqlGeneratorAllinOneFile("MYSQL","/home/ronald/TesisOutput");
			 sqlGenerator.execute();

			// Sql Generator - Different files per package
			// SqlGenerator sqlGenerator = new
			// SqlGenerator("MYSQL","/home/ronald/TesisOutput");
			// sqlGenerator.execute();

//			String company = "MOBI";
//			// String outputFolder = "C:\\Users\\Ronald\\Tesis";
//			String outputFolder = "/home/ronald/TesisOutput";
//			String outputUpperPackage = "mobile.entity";
//			EntityGenerator entityGenerator = new EntityGenerator(company,
//					outputFolder, outputUpperPackage);
//			entityGenerator.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPManager.close();
			JPManagerFactory.close();
		}

	}

}
