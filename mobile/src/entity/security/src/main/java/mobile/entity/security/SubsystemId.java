package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the SUBSYSTEM_ID database table.
 */
@Entity
@Table(name = "SUBSYSTEM_ID")
public class SubsystemId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Subsystem Id
	 */
	@Id
	@Column(name = "SUBSYSTEM_ID", nullable = false)
	private String subsystemId;

	public SubsystemId() {
	}

	public SubsystemId(String subsystemId) {
		this.subsystemId = subsystemId;
	}

	public String getSubsystemId() {
		return this.subsystemId;
	}

	public void setSubsystemId(String subsystemId) {
		this.subsystemId = subsystemId;
	}

	@Override
	public Object getPk() {
		return this.subsystemId;
	}

	@Override
	public void setPk(Object pk) {
		this.subsystemId = (String) pk;
	}

	@Override
	public String toString() {
		return "SUBSYSTEM_ID:[" + this.getPk().toString() + "]";
	}
}
