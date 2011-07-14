package microxt.entity.generator;

import microxt.entity.util.JpaManager;
import microxt.entity.util.PersistenceFactory;

public class Generator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Initialize Jpa persistence
			PersistenceFactory.createEntityManagerFactory();
			JpaManager.createEntityManager();

			//Execute sql generator
			/*
			SqlGenerator sqlGenerator = new SqlGenerator("HNDT","MYSQL");
			sqlGenerator.execute();
			*/
			
			// Execute entity generator
			EntityGenerator entityGenerator = new EntityGenerator("ALL","/home/ronald");
			entityGenerator.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			//Close Jpa persistence
			JpaManager.close();
			PersistenceFactory.close();
		}
	}
}
