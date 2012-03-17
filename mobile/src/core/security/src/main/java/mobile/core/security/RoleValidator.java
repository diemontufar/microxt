package mobile.core.security;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import mobile.common.message.Message;
import mobile.entity.manager.JpManager;
import mobile.entity.security.Role;
import mobile.tools.common.Objection;
import mobile.tools.common.enums.ObjectionCode;
import mobile.tools.common.param.LocalParameter;
import mobile.tools.common.param.ParameterEnum;
import mobile.tools.common.param.Timer;

public class RoleValidator {

	private final String ROLE_PERMISSION_QL = "SELECT o FROM Role o WHERE o.pk.companyId = :companyId "
			+ "AND o.pk.expired = :expired AND o.pk.profileId = :profileId AND o.pk.subsystemId = :subsystemId "
			+ "AND o.pk.moduleId = :moduleId AND o.pk.processId = :processId ";

	public Message execute(Message msg) throws Exception {
		String process = msg.getRequest().getProcess();
		if (process != null) {
			String subsystemId = process.substring(0, 1);
			String moduleId = process.substring(1, 2);
			String processId = process.substring(2);
			String profileId = msg.getRequest().getProfile();
			if (!isValidRole(profileId, subsystemId, moduleId, processId, msg)) {
				throw new Objection(ObjectionCode.ROLE_PROCESS, process, profileId);
			}
		}
		return msg;
	}

	private boolean isValidRole(String profileId, String subsystemId, String moduleId, String processId, Message message)
			throws Exception {
		try {
			Role role = getRole(profileId, subsystemId, moduleId, processId);
			if (role != null) {
				// message.getRequest().addField(Field.REQUEST_EDITABLE,
				// role.isEditable());
			}
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

	private Role getRole(String profileId, String subsystemId, String moduleId, String processId) throws Exception {
		TypedQuery<Role> query = JpManager.getEntityManager().createQuery(ROLE_PERMISSION_QL, Role.class);
		query.setParameter("companyId", LocalParameter.get(ParameterEnum.COMPANY, String.class));
		query.setParameter("expired", Timer.getExpiredTime());
		query.setParameter("profileId", profileId);
		query.setParameter("subsystemId", subsystemId);
		query.setParameter("moduleId", moduleId);
		query.setParameter("processId", processId);
		return query.getSingleResult();
	}
}