package mobile.entity.security;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.HistoricalKey;
import mobile.entity.schema.MulticompanyKey;

/**
 * The primary key class for the USER_AUTHORIZATION database table.
 */
@Embeddable
public class UserAuthorizationPk extends AbstractCompanyHistoricalKey implements
		MulticompanyKey, HistoricalKey {
	private static final long serialVersionUID = 1L;

	/**
	 * User Id
	 */
	@Column(name = "USER_ID", nullable = false)
	private String userId;

	/**
	 * Authorizer Id
	 */
	@Column(name = "AUTHORIZER_ID", nullable = false)
	private String authorizerId;

	/**
	 * Subsystem Id
	 */
	@Column(name = "SUBSYSTEM_ID", nullable = false)
	private String subsystemId;

	/**
	 * Module Id
	 */
	@Column(name = "MODULE_ID", nullable = false)
	private String moduleId;

	/**
	 * Process Id
	 */
	@Column(name = "PROCESS_ID", nullable = false)
	private String processId;

	public UserAuthorizationPk() {
	}

	public UserAuthorizationPk(String userId, String authorizerId,
			String subsystemId, String moduleId, String processId) {
		this.userId = userId;
		this.authorizerId = authorizerId;
		this.subsystemId = subsystemId;
		this.moduleId = moduleId;
		this.processId = processId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAuthorizerId() {
		return this.authorizerId;
	}

	public void setAuthorizerId(String authorizerId) {
		this.authorizerId = authorizerId;
	}

	public String getSubsystemId() {
		return this.subsystemId;
	}

	public void setSubsystemId(String subsystemId) {
		this.subsystemId = subsystemId;
	}

	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getProcessId() {
		return this.processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getExpired() + ", "
				+ this.getUserId() + ", " + this.getAuthorizerId() + ", "
				+ this.getSubsystemId() + ", " + this.getModuleId() + ", "
				+ this.getProcessId() + "]";
	}
}
