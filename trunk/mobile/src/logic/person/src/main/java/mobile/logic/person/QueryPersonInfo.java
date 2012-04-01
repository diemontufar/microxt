package mobile.logic.person;

import java.util.List;

import javax.persistence.Query;

import mobile.common.message.EntityData;
import mobile.common.message.Message;
import mobile.entity.manager.JpManager;
import mobile.entity.manager.util.QueryUtil;
import mobile.tools.common.structure.QueryProcessor;

public class QueryPersonInfo implements QueryProcessor {

	private final String PERSON_QL = "Select a.PERSON_ID, a.IDENTIFICATION_NUMBER, "
			+ "if(a.SECOND_LAST_NAME is not null, CONCAT(a.LAST_NAME, ' ', a.SECOND_LAST_NAME, ' ', a.NAME), CONCAT(a.LAST_NAME, ' ', a.NAME)) NAME "
			+ "from PERSON a  " + "where a.COMPANY_ID = 'MXT' and a.EXPIRED = fncexpired() order by a.PERSON_ID";

	@SuppressWarnings("unchecked")
	@Override
	public Message process(Message msg) throws Exception {
		EntityData data = msg.getEntityData("Person");

		QueryUtil util = new QueryUtil(false);

		Query query = JpManager.getEntityManager().createNativeQuery(util.completeSQL(PERSON_QL, data));
		List<Object[]> results = query.getResultList();

		util.listToData(results, data);

		return msg;
	}
}
