package mobile.core.processor;

import java.util.List;

import javax.persistence.Query;

import mobile.entity.manager.JPManager;
import mobile.entity.manager.JPManagerFactory;
import mobile.entity.microcredit.Recommendation;
import mobile.tools.common.Log;
import mobile.tools.common.param.LocalParameter;
import mobile.tools.common.param.ParameterEnum;
import mobile.tools.common.param.Timer;

import org.apache.log4j.Logger;
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ProcessorTest {

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
	@Ignore
	@Test
	public void testJpa() {
		try {
			// String sql =
			// "Select e.pk.profileId, " +
			// "(Select b.url from Process b where b.pk.subsystemId=e.pk.subsystemId "
			// +
			// "and b.pk.moduleId=e.pk.moduleId and b.pk.processId=e.pk.processId "
			// +
			// "and b.pk.expired = '9999-12-31'  and b.pk.companyId = 'MXT' ), "
			// +
			// "e.editable " +
			// "from Role e where e.pk.expired = :expired and e.pk.companyId = :companyId";

			String sql = "Select e.pk.profileId, e.editable, " + "p.url " + "from Role e "
					+ "inner join Process p on p.pk.companyId=e.pk.companyId and p.pk.expired=e.pk.expired "
					+ "and p.pk.subsystemId=e.pk.subsystemId and p.pk.moduleId=e.pk.moduleId "
					+ "and p.pk.processId=e.pk.processId";

			// String sql =
			// "Select e.pk.profileId, " +
			// "e.editable " +
			// "from Role e where e.pk.expired = :expired and e.pk.companyId = :companyId";

			JPManager.createEntityManager();

			Query query = JPManager.getEntityManager().createQuery(sql);
			// query.setParameter("expired", PersistenceTime.getExpiredTime());
			// query.setParameter("companyId", "MXT");

			query.setHint(QueryHints.READ_ONLY, HintValues.TRUE);

			List results = query.getResultList();

			// Pagination:
			for (int i = 0; i < results.size(); i++) {
				Object[] result = (Object[]) results.get(i);

				for (Object obj : result) {
					System.out.print(obj + " ");
				}
				System.out.println();
			}

			JPManager.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Ignore
	@Test
	public void testQuery() {
		try {
			String sql = "Select a from Recommendation a where a.pk.solicitudeId = ?1";
			//String sql = "Select a from Recommendation a where a.pk.expired = ?1 and a.pk.solicitudeId = ?2";
			//String sql = "Select * from RECOMMENDATION a where a.SOLICITUDE_ID = ?1";
			
			//String sql = "Select a from Solicitude a where a.pk.solicitudeId = ?1";
			
			JPManager.createEntityManager();

			Query query = JPManager.getEntityManager().createQuery(sql);
			//Query query = JPManager.getEntityManager().createNativeQuery(sql);
			//query.setHint(QueryHints.READ_ONLY, HintValues.TRUE);
			query.setParameter(1,new Integer(100));
			

			List results = query.getResultList();

			// Pagination:
			for (int i = 0; i < results.size(); i++) {
				Object[] result = (Object[]) results.get(i);

				for (Object obj : result) {
					System.out.print(obj + " ");
				}
				System.out.println();
			}

			JPManager.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
