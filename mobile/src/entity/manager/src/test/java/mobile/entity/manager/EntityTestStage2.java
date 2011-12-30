package mobile.entity.manager;

import java.util.List;

import junit.framework.TestCase;
import mobile.entity.schema.GeneralEntity;

import org.junit.Before;

public class EntityTestStage2 extends TestCase {

	private List<GeneralEntity> entities;

	@Before
	public void setUp() throws Exception {
		TestObjects testObjects = new TestObjects();
		entities = testObjects.getEntities();
	}
}
