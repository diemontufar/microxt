package mobile.entity.manager;

import java.math.BigDecimal;

import mobile.entity.microcredit.Solicitude;
import mobile.entity.microcredit.SolicitudePk;
import mobile.tools.common.param.LocalParameter;
import mobile.tools.common.param.ParameterEnum;
import mobile.tools.common.param.Timer;

import org.junit.Test;

public class SequentialTest {

	@Test
	public void testSequential() {
		try {
			JPManagerFactory.createEntityManagerFactory();
			JPManager.createEntityManager();
			LocalParameter.set(ParameterEnum.COMPANY, "MXT");
			LocalParameter.set(ParameterEnum.LANGUAGE, "ES");
			JPManager.beginTransaction();
			
			SolicitudePk pk = new SolicitudePk();
			Solicitude a = new Solicitude(pk, "ADM", Timer.getCurrentDate(), Timer.getCurrentDate(), "M01", "001", 0, new BigDecimal("100"), 90L, "MNL", 3, "4", "PRO", "ASD");
			JPManager.persist(a);
			
			JPManager.commitTransaction();
		} catch (Exception e) {
			JPManager.rollbackTransaction();
			e.printStackTrace();
		} finally {
			JPManager.close();
			JPManagerFactory.close();
		}
	}
}
