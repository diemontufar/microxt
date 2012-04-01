package mobile.logic.microxt.query;

import java.util.List;

import javax.persistence.Query;

import mobile.common.message.EntityData;
import mobile.common.message.Message;
import mobile.entity.manager.JpManager;
import mobile.entity.manager.util.QueryUtil;
import mobile.tools.common.structure.QueryProcessor;

public class QueryPartnerInfo implements QueryProcessor {

	private final String PARTNER_QL = "Select a.partner_id, b.identification_number, "
			+ "if(b.second_last_name is not null, CONCAT(b.last_name, ' ', b.second_last_name, ' ', b.name), CONCAT(b.last_name, ' ', b.name)) name "
			+ "from PARTNER a "
			+ "inner join PERSON b on a.company_id = b.company_id and a.expired = b.expired and a.person_id = b.person_id "
			+ "where a.expired = fncexpired() order by partner_id";

	@SuppressWarnings("unchecked")
	@Override
	public Message process(Message msg) throws Exception {
		EntityData data = msg.getEntityData("Partner");
		QueryUtil util = new QueryUtil(false);

		Query query = JpManager.getEntityManager().createNativeQuery(util.completeSQL(PARTNER_QL, data));
		List<Object[]> results = query.getResultList();

		util.listToData(results, data);

		return msg;
	}
}
