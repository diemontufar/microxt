package mobile.logic.microxt.query;

import java.util.List;

import javax.persistence.Query;

import mobile.common.message.EntityData;
import mobile.common.message.Message;
import mobile.entity.manager.JpManager;
import mobile.entity.manager.util.QueryUtil;
import mobile.tools.common.structure.QueryProcessor;

public class DayPayments implements QueryProcessor {

	private final String PAYMENTS_SQL = "Select account_id, subaccount, capital, interest, capital+interest from MICRO_ACCOUNT_QUOTA"
			+ "where company_id='MXT' and expired=fncexpired() and payment_date=now()";

	@SuppressWarnings("unchecked")
	@Override
	public Message process(Message msg) throws Exception {
		EntityData data = msg.getEntityData("MicroAccountQuota");
		QueryUtil util = new QueryUtil(false);

		Query query = JpManager.getEntityManager().createNativeQuery(util.completeSQL(PAYMENTS_SQL, data));
		List<Object[]> results = query.getResultList();

		util.listToData(results, data);

		return msg;
	}

}
