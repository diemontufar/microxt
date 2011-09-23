package mobile.core.generator;

import mobile.entity.manager.JPManager;
import mobile.entity.manager.JPManagerFactory;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Initialize Jpa
			JPManagerFactory.createEntityManagerFactory();
			JPManager.createEntityManager();
			System.out.println("Hola mundo...");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JPManager.close();
			JPManagerFactory.close();
		}
	}
}
