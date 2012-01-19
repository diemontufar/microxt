package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the SUBSYSTEM database table.
 */
@Embeddable
public class SubsystemPk extends AbstractCompanyLanguageKey implements MulticompanyKey, MultilanguageKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Subsystem Id
	 */
	@Column(name = "SUBSYSTEM_ID", nullable = false)
	private String subsystemId;

	public SubsystemPk() {
	}

	public SubsystemPk(String subsystemId) {
		this.subsystemId = subsystemId;
	}

	public String getSubsystemId() {
		return this.subsystemId;
	}

	public void setSubsystemId(String subsystemId) {
		this.subsystemId = subsystemId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getSubsystemId() + "]";
	}
}
