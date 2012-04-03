package mobile.logic.microxt.query;

import java.util.List;

import javax.persistence.Query;

import mobile.common.message.EntityData;
import mobile.common.message.Message;
import mobile.entity.manager.JpManager;
import mobile.entity.manager.util.QueryUtil;
import mobile.tools.common.param.LocalParameter;
import mobile.tools.common.param.ParameterEnum;
import mobile.tools.common.param.Timer;
import mobile.tools.common.structure.QueryProcessor;

/**
 * Query solicitude information for consulting .
 * Allow filters
 * If admin profile query all information otherwise query only his solicitudes 
 */
public class QuerySolicitude implements QueryProcessor {

	private final String SOLICITUDE_QL = "select a.SOLICITUDE_ID, a.ASSESSOR, "
			+ "if(a.PARTNER_CLIENT_ID is not null, 'INDIVIDUAL', 'GRUPAL') TIPO_CLIENTE, "
			+ "if(a.PARTNER_CLIENT_ID is not null,  "
			+ "    if(c.SECOND_LAST_NAME is not null, CONCAT(c.LAST_NAME, ' ', c.SECOND_LAST_NAME, ' ', c.NAME), CONCAT(c.LAST_NAME, ' ', c.NAME)), "
			+ "    (select group_description from PARTNER_GROUP where company_id=?1 and language_id=?2 and expired=?3 and partner_group_id= a.group_client_id)) NOMBRE_CLIENTE, "
			+ "a.SOLICITUDE_DATE, a.PRODUCT_ID,  "
			+ "(Select DESCRIPTION from SOLICITUDE_STATUS where STATUS_ID=a.STATUS_ID and COMPANY_ID = ?1 ) STATUS, "
			+ "if(a.INSTRUMENTATION_DATE is not null, 'SI', 'NO') INSTRUMENTED, "
			+ "a.AMOUNT, a.TERM, a.QUOTA_TYPE_ID,  "
			+ "(Select DESCRIPTION from FREQUENCY where FREQUENCY_ID=a.PAYMENT_FREQUENCY_ID and COMPANY_ID = ?1 ) FREQ, "
			+ "(Select DESCRIPTION from FUNDS_DESTINATION where FUNDS_DESTINATION_ID=a.FUNDS_DESTINATION_ID and COMPANY_ID=?1 and language_id=?2) FUNDS_DESTINATION "
			+ "from SOLICITUDE a "
			+ "left outer join PARTNER b on b.company_id=a.company_id and b.language_id=a.language_id and b.expired=a.expired and b.partner_id=a.partner_client_id "
			+ "left outer join PERSON c on c.company_id=b.company_id and c.expired=b.expired and c.person_id=b.person_id "
			+ "where a.EXPIRED = ?3 and a.COMPANY_ID = ?1 and a.ASSESSOR like ?4";

	@SuppressWarnings("unchecked")
	@Override
	public Message process(Message msg) throws Exception {
		EntityData data = msg.getEntityData("Solicitude");

		QueryUtil util = new QueryUtil(false);
		String sql = util.completeSQL(SOLICITUDE_QL, data);

		Query query = JpManager.getEntityManager().createNativeQuery(sql);
		query.setParameter(1, LocalParameter.get(ParameterEnum.COMPANY, String.class));
		query.setParameter(2, LocalParameter.get(ParameterEnum.LANGUAGE, String.class));
		query.setParameter(3, Timer.getExpiredTime());
		if (msg.getRequest().getProfile().compareTo("ADM") == 0) {
			query.setParameter(4, "%");
		}else{
			query.setParameter(4, msg.getRequest().getUser());
		}

		List<Object[]> results = query.getResultList();

		util.listToData(results, data);

		return msg;
	}
}
