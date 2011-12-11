package mobile.entity.manager;

import java.util.List;

import junit.framework.TestCase;
import mobile.entity.schema.GeneralEntity;
import mobile.tools.common.param.LocalParameter;
import mobile.tools.common.param.ParameterEnum;

import org.junit.Before;
import org.junit.Test;

public class EntityTestStage3 extends TestCase {

	private List<GeneralEntity> entities;

	@Before
	public void setUp() throws Exception {
		TestObjects testObjects = new TestObjects();
		entities = testObjects.getEntities();
	}

	@Test
	public void test() {
		try {

			// Initialize global parameters
			LocalParameter.set(ParameterEnum.COMPANY, "MXT");
			LocalParameter.set(ParameterEnum.LANGUAGE, "ES");
			// Initialize Jpa
			JPManagerFactory.createEntityManagerFactory("central");
			JPManager.createEntityManager();
			// Begin tx
			JPManager.beginTransaction();

			// Deletes
			for (int i = entities.size() - 1; i >= 0; i--) {
				GeneralEntity ent = entities.get(i);
				GeneralEntity res = JPManager.find(ent.getClass(), ent.getPk());
				if (res != null) {
					System.out.println("DELETE: " + ent);
					JPManager.delete(ent);
				}
			}

			// Commit tx
			JPManager.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			JPManager.rollbackTransaction();
		} finally {
			JPManager.close();
			JPManagerFactory.close();
		}
	}
}
