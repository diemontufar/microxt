package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;
import mobile.entity.schema.HistoricalKey;

/**
 * The primary key class for the PROCESS database table.
 */
@Embeddable
public class ProcessPk extends AbstractCompanyLanguageHistoricalKey implements MulticompanyKey, MultilanguageKey,
		HistoricalKey {
	private static final long serialVersionUID = 1L;

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

	public ProcessPk() {
	}

	public ProcessPk(String subsystemId, String moduleId, String processId) {
		this.subsystemId = subsystemId;
		this.moduleId = moduleId;
		this.processId = processId;
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
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getExpired() + ", "
				+ this.getSubsystemId() + ", " + this.getModuleId() + ", " + this.getProcessId() + "]";
	}
}
