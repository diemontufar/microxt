package mobile.core.processor;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import mobile.entity.manager.JPManager;
import mobile.entity.manager.JPManagerFactory;
import mobile.entity.microcredit.Solicitude;
import mobile.tools.common.Log;
import mobile.tools.common.param.LocalParameter;
import mobile.tools.common.param.ParameterEnum;

import org.apache.log4j.Logger;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QueryTest {

	Logger log = Log.getInstance();

	@Before
	public void setUp() throws Exception {
		JPManagerFactory.createEntityManagerFactory();
		LocalParameter.set(ParameterEnum.COMPANY, "MXT");
		LocalParameter.set(ParameterEnum.LANGUAGE, "ES");
	}

	@After
	public void tearDown() throws Exception {
		JPManagerFactory.close();
	}

	@SuppressWarnings("rawtypes")
	// @Ignore
	@Test
	public void testQuery() {
		try {
			String sql = "Select x from (Select a.pk.solicitudeId, b.description " +
					"from Solicitude a ) as x";

			JPManager.createEntityManager();

			Query query = JPManager.getEntityManager().createQuery(sql);
			//TypedQuery<Solicitude> query = JPManager.getEntityManager().createQuery(sql, Solicitude.class);
			// query.setParameter("expired", PersistenceTime.getExpiredTime());
			// query.setParameter("companyId", "MXT");
			query.setHint(QueryHints.READ_ONLY, HintValues.TRUE);

			List<Object[]> results = query.getResultList();
			//List<Solicitude> results = query.getResultList();
			// Long maxCount = (Long) query.getSingleResult();
			// System.out.println("MAX: " + maxCount);

			// Reading0:
			for (int i = 0; i < results.size(); i++) {
				Object[] result = (Object[]) results.get(i);

				for (Object obj : result) {
					System.out.print(obj + " ");
				}
				System.out.println();
			}

			// Reading1:
//			for (Solicitude sol : results) {
//				System.out.println(sol);
//			}

			JPManager.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
