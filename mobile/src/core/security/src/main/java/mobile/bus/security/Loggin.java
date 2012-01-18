package mobile.bus.security;

import mobile.core.structure.processor.GeneralProcessor;
import mobile.entity.manager.JPManager;
import mobile.entity.security.UserAccess;
import mobile.entity.security.UserAccessPk;
import mobile.entity.security.UserAccount;
import mobile.entity.security.UserAccountPk;
import mobile.message.message.EntityData;
import mobile.message.message.Field;
import mobile.message.message.Message;
import mobile.tools.common.Log;

import org.apache.log4j.Logger;

public class Loggin implements GeneralProcessor{

	private final String LOGGIN_DATA = "loggin";

	private final Logger log = Log.getInstance();
	
	@Override
	public Message process(Message msg) throws Exception {
		EntityData logginData = msg.getEntityData(LOGGIN_DATA);
		
		String user = logginData.getField("user").getValue();
		String password = logginData.getField("password").getValue();
		
		// Verify
		UserAccountPk userPk = new UserAccountPk(user);
		UserAccount userAcc = JPManager.find(UserAccount.class, userPk);

		if (userAcc != null) {
			log.info("User found: ");
			log.info(userAcc);

			// Find the password and compare
			UserAccessPk accessPk = new UserAccessPk(user);
			UserAccess userAccess = JPManager.find(UserAccess.class,
					accessPk);

			if (userAccess != null
					&& userAccess.getUserKey().compareTo(password) == 0) {
				log.info("Verified password");
				log.info(userAccess);
			} else {
				log.info("Error de autenticacion");
			}
		}

		if(user.compareToIgnoreCase("admin")==0 && password.compareToIgnoreCase("admin")==0){
			logginData.addField(new Field("authorized", "1"));
		}else{
			logginData.addField(new Field("authorized", "0"));
		}
		
		return msg;
	}

}
