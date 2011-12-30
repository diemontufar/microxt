package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the MODULE database table.
 */
@Embeddable
public class ModulePk extends AbstractCompanyLanguageKey implements
		MulticompanyKey, MultilanguageKey {
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

	public ModulePk() {
	}

	public ModulePk(String subsystemId, String moduleId) {
		this.subsystemId = subsystemId;
		this.moduleId = moduleId;
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

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", "
				+ this.getSubsystemId() + ", " + this.getModuleId() + "]";
	}
}
