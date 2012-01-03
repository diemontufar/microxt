package mobile.entity.manager;

import java.util.List;

import javax.persistence.TypedQuery;

import mobile.entity.microcredit.ProductMicrocredit;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
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
			JPManagerFactory.createEntityManagerFactory("generator");
			JPManager.createEntityManager();

			TypedQuery<ProductMicrocredit> query = JPManager.getEntityManager()
					.createQuery("Select p from ProductMicrocredit p",
							ProductMicrocredit.class);
			query.setHint(QueryHints.READ_ONLY, HintValues.TRUE);
			List<ProductMicrocredit> list = query.getResultList();
			for (ProductMicrocredit product : list) {
				System.out.println(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JPManager.close();
			JPManagerFactory.close();
		}
	}

}
