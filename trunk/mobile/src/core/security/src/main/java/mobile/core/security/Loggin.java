package mobile.core.security;

import mobile.common.message.Message;
import mobile.entity.manager.JpManager;
import mobile.entity.security.UserAccess;
import mobile.entity.security.UserAccessPk;
import mobile.entity.security.UserAccount;
import mobile.entity.security.UserAccountPk;
import mobile.tools.common.structure.QueryProcessor;

public class Loggin implements QueryProcessor {

	@Override
	public Message process(Message msg) throws Exception {
		String user = msg.getControlFieldValue("user");
		String password = msg.getControlFieldValue("password");

		String code = "0";
		String message = "Autenticación fallida";

		// Verify
		UserAccountPk userPk = new UserAccountPk(user);
		UserAccount userAcc = JpManager.find(UserAccount.class, userPk);

		if (userAcc != null) {
			log.info("User found: ");
			log.info(userAcc);

			// Find the password and compare
			UserAccessPk accessPk = new UserAccessPk(user);
			UserAccess userAccess = JpManager.find(UserAccess.class, accessPk);

			if (userAccess != null && userAccess.getUserKey().compareTo(password) == 0) {
				log.info("Verified password");
				log.info(userAccess);
				code = "1";
				message = "Autenticación correcta";
			} else {
				log.info("Error de autenticacion");
				message = "La contraseña ingresada es incorrecta";
			}
		} else {
			message = "El usuario " + user + " no existe";
		}

		msg.setControlFieldValue("responseCode", code);
		msg.setControlFieldValue("responseMessage", message);

		return msg;
	}
}