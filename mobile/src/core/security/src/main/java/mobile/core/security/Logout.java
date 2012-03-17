package mobile.core.security;

import mobile.common.message.Message;
import mobile.entity.manager.JpManager;
import mobile.entity.security.UserSession;
import mobile.entity.security.UserSessionPk;
import mobile.tools.common.structure.MaintenanceProcessor;

public class Logout implements MaintenanceProcessor {

	@Override
	public Message process(Message msg) throws Exception {
		String userId = msg.getRequest().getUser();
		String hostId = msg.getRequest().getHost();

		UserSessionPk sessionPk = new UserSessionPk(userId, hostId);
		UserSession session = JpManager.find(UserSession.class, sessionPk);

		if (session != null) {
			JpManager.delete(session);
		}
		
		return msg;
	}
}