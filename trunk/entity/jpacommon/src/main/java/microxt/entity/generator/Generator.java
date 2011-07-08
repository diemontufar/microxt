package microxt.entity.generator;

import microxt.entity.util.JpaManager;
import microxt.entity.util.PersistenceFactory;

public class Generator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Initialize Jpa
			PersistenceFactory.createEntityManagerFactory();
			JpaManager.createEntityManager();

			SqlGenerator sqlGenerator = new SqlGenerator();
			sqlGenerator.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JpaManager.close();
			PersistenceFactory.close();
		}

	}

}
