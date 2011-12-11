package mobile.entity.manager;

import java.util.List;

import junit.framework.TestCase;
import mobile.entity.parameter.Branch;
import mobile.entity.parameter.Parameter;
import mobile.entity.schema.GeneralEntity;
import mobile.tools.common.param.LocalParameter;
import mobile.tools.common.param.ParameterEnum;

import org.junit.Before;
import org.junit.Test;

public class EntityTestStage2 extends TestCase {

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

			// Updates
			// Parameter
			Parameter param = (Parameter) entities.get(0);
			param = JPManager.find(param.getClass(), param.getPk());
			param.setDescription("11111111111111");
			JPManager.update(param);
			// Branch
			Branch branch = (Branch) entities.get(1);
			branch = JPManager.find(branch.getClass(), branch.getPk());
			branch.setName("1111111111");
			JPManager.update(branch);

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
