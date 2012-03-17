package mobile.core.security;

import java.util.List;

import javax.persistence.TypedQuery;

import mobile.common.message.Message;
import mobile.entity.manager.JpManager;
import mobile.entity.security.UserSession;
import mobile.entity.security.UserSessionPk;
import mobile.tools.common.Encoder;
import mobile.tools.common.Objection;
import mobile.tools.common.enums.ObjectionCode;
import mobile.tools.common.param.LocalParameter;
import mobile.tools.common.param.ParameterEnum;
import mobile.tools.common.param.Timer;

public class SessionValidator {

	private final String USER_SESSION_QL = "SELECT o FROM UserSession o WHERE o.pk.companyId = :companyId "
			+ "AND o.pk.expired = :expired AND o.pk.userId = :userId";

	public Message execute(Message msg) throws Exception {
		String userId = msg.getRequest().getUser();
		String hostId = msg.getRequest().getHost();
		String sessionId = msg.getRequest().getSession();

		UserSession userSession = getUserSession(userId, hostId);

		if (userSession != null && userSession.getSessionId().compareTo(sessionId) != 0) {
			throw new Objection(ObjectionCode.NO_SESSION, userId);
		}
		return msg;
	}

	public Message setUserSession(Message msg) throws Exception {
		String userId = msg.getRequest().getUser();
		String hostId = msg.getRequest().getHost();

		List<UserSession> userSessionList = getUserSessionList(userId);

		if (userSessionList == null || userSessionList.size() == 0) {
			UserSessionPk usPk = new UserSessionPk(userId, hostId);
			UserSession us = new UserSession(usPk);

			String time = String.valueOf(Timer.getCurrentTime());
			String sessionId = Encoder.encrypt(userId + hostId) + time.replaceAll("\\D", "");

			us.setSessionId(sessionId);
			JpManager.persist(us);
			msg.getRequest().setSession(sessionId);
		} else if (userSessionList.size() == 1 && userSessionList.get(0).getPk().getHostId().compareTo(hostId) == 0) {
			msg.getRequest().setSession(userSessionList.get(0).getSessionId());
		} else if (userSessionList.size() == 1 && userSessionList.get(0).getPk().getHostId().compareTo(hostId) != 0) {
			throw new Objection(ObjectionCode.USER_SESSION, userId, userSessionList.get(0).getPk().getHostId());
		} 
		
		return msg;
	}

	private UserSession getUserSession(String userId, String hostId) throws Exception {
		UserSessionPk userSessionPk = new UserSessionPk(userId, hostId);
		return JpManager.find(UserSession.class, userSessionPk);
	}

	private List<UserSession> getUserSessionList(String userId) throws Exception {
		TypedQuery<UserSession> query = JpManager.getEntityManager().createQuery(USER_SESSION_QL, UserSession.class);
		query.setParameter("companyId", LocalParameter.get(ParameterEnum.COMPANY, String.class));
		query.setParameter("expired", Timer.getExpiredTime());
		query.setParameter("userId", userId);
		return query.getResultList();
	}
}
