package mobile.core.security;

import mobile.common.message.Message;
import mobile.entity.manager.JpManager;
import mobile.entity.security.UserAccess;
import mobile.entity.security.UserAccessPk;
import mobile.tools.common.Objection;
import mobile.tools.common.enums.ObjectionCode;
import mobile.tools.common.param.Timer;
import mobile.tools.common.structure.MaintenanceProcessor;

public class ChangePassword implements MaintenanceProcessor {

	private final static String OLD_PASSWORD = "oldPassword";
	private final static String PASSWORD = "newPassword";

	@Override
	public Message process(Message msg) throws Exception {
		String user = msg.getRequest().getUser();
		String oldPassword = msg.getControlFieldValue(OLD_PASSWORD);
		String newPassword = msg.getControlFieldValue(PASSWORD);

		// Verify old password
		UserAccessPk oldAccessPk = new UserAccessPk(user);
		UserAccess oldUserAccess = JpManager.find(UserAccess.class, oldAccessPk);
		if (oldUserAccess.getUserKey().compareTo(oldPassword) != 0) {
			throw new Objection(ObjectionCode.FAILED_, "La contraseña anterior no es correcta");
		}

		// Persist changes
		oldUserAccess.setUserKey(newPassword);
		oldUserAccess.setLastChange(Timer.getCurrentDate());
		JpManager.update(oldUserAccess);

		return msg;
	}
}