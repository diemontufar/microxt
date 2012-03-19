package mobile.core.security.query;

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

public class QueryProfilesForUser implements QueryProcessor {

	private final String PROFILES_QL = "SELECT PROFILE_ID, DESCRIPTION FROM PROFILE WHERE PROFILE_ID IN ( " +
    "SELECT PROFILE_ID FROM USER_PROFILE WHERE COMPANY_ID = ?1 AND EXPIRED = ?2 AND USER_ID = ?3)";

	@SuppressWarnings("unchecked")
	@Override
	public Message process(Message msg) throws Exception {
		EntityData data = msg.getEntityData("Profile");
		String user = data.getLfilters().get(0).getValue();

		Query query = JpManager.getEntityManager().createNativeQuery(PROFILES_QL);
		query.setParameter(1, LocalParameter.get(ParameterEnum.COMPANY, String.class));
		query.setParameter(2, Timer.getExpiredTime());
		query.setParameter(3, user);
		List<Object[]> results = query.getResultList();

		QueryUtil util = new QueryUtil(false);
		util.listToData(results, data);

		return msg;
	}
}