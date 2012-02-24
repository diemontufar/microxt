package mobile.entity.manager;

import org.junit.Ignore;
import org.junit.Test;

public class BasicTest {

	@Ignore
	@Test
	public void testConection() {
		try {
			// Initialize Jpa
			JPManagerFactory.createEntityManagerFactory();
			JPManager.createEntityManager();
			System.out.println("Hola mundo");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPManager.close();
			JPManagerFactory.close();
		}
	}

	@Test
	public void testObject() {
		try {
			JPManagerFactory.createEntityManagerFactory("central");
			JPManager.createEntityManager();

//			TypedQuery<GeographicZone> query = JPManager.getEntityManager()
//					.createQuery("Select p from GeographicZone p",
//							GeographicZone.class);
//			query.setHint(QueryHints.READ_ONLY, HintValues.TRUE);
//			List<GeographicZone> list = query.getResultList();
//			for (GeographicZone ent : list) {
//				System.out.println(ent);
//			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPManager.close();
			JPManagerFactory.close();
		}
	}

}
