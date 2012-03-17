package mobile.core.security;

import mobile.common.message.Message;
import mobile.entity.manager.JpManager;
import mobile.entity.security.UserAccess;
import mobile.entity.security.UserAccessPk;
import mobile.entity.security.UserAccount;
import mobile.entity.security.UserAccountPk;
import mobile.tools.common.Objection;
import mobile.tools.common.enums.ObjectionCode;
import mobile.tools.common.structure.QueryProcessor;

public class Loggin implements QueryProcessor {

	private final static String USER = "user";
	private final static String PASSWORD = "password";
	private final static String HOST = "host";
	private final static String CHANNEL = "channel";
	private final static String SESSION = "session";
	private final static String PROFILE = "profile";
	private final static String RESPONSE_CODE = "responseCode";
	private final static String RESPONSE_MSG = "responseMessage";

	@Override
	public Message process(Message msg) throws Exception {
		String user = msg.getControlFieldValue(USER);
		String password = msg.getControlFieldValue(PASSWORD);

		try {
			// Verify/set host
			HostValidator hv = new HostValidator();
			hv.setHostId(msg);

			// Verify user and password
			UserAccountPk userPk = new UserAccountPk(user);
			UserAccount userAcc = JpManager.find(UserAccount.class, userPk);

			if (userAcc != null) {
				log.info("User found: " + userAcc);
				msg.getRequest().setUser(user);

				// Verify user status
				if (userAcc.getUserStatusId().compareTo("ACT") != 0) {
					throw new Objection(ObjectionCode.USER_NOT_ACTIVE, userAcc.getPk().getUserId());
				}

				// Verify password
				UserAccessPk accessPk = new UserAccessPk(user);
				UserAccess userAccess = JpManager.find(UserAccess.class, accessPk);

				if (userAccess != null && userAccess.getUserKey().compareTo(password) == 0) {
					log.info("Verified password");
					log.info(userAccess);

					SessionValidator sv = new SessionValidator();
					sv.setUserSession(msg);
					
//					RoleValidator rv = new RoleValidator();
//					rv.execute(msg);
				} else {
					log.info("Ivalid password");
					throw new Objection(ObjectionCode.USER_PASSWORD);
				}
			} else {
				log.info("Not defined user");
				throw new Objection(ObjectionCode.USER_NOT, user);
			}

		} catch (Objection e) {
			if(e instanceof Objection){
				msg.setControlFieldValue(RESPONSE_CODE, "0");
				msg.setControlFieldValue(RESPONSE_MSG, e.getMessage());
				return msg;
			}else{
				throw e;
			}
		}
		
		msg.setControlFieldValue(HOST, msg.getRequest().getHost());
		msg.setControlFieldValue(CHANNEL, msg.getRequest().getChannel());
		msg.setControlFieldValue(SESSION, msg.getRequest().getSession());
		msg.setControlFieldValue(PROFILE, msg.getRequest().getProfile());
		msg.setControlFieldValue(RESPONSE_CODE, "1");
		msg.setControlFieldValue(RESPONSE_MSG, "USUARIO LOGEADO CORRECTAMENTE");
		
		return msg;
	}
}