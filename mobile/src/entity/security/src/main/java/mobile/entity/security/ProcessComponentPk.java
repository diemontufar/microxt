package mobile.entity.security;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import mobile.entity.schema.AbstractCompanyKey;
import mobile.entity.schema.MulticompanyKey;

/**
 * The primary key class for the PROCESS_COMPONENT database table.
 */
@Embeddable
public class ProcessComponentPk extends AbstractCompanyKey implements
		MulticompanyKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Channel Id
	 */
	@Column(name = "CHANNEL_ID", nullable = false)
	private String channelId;

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

	/**
	 * Process sequence
	 */
	@Column(name = "PROCESS_SEQUENCE", nullable = false)
	private Integer processSequence;

	public ProcessComponentPk() {
	}

	public ProcessComponentPk(String channelId, String subsystemId,
			String moduleId, String processId, Integer processSequence) {
		this.channelId = channelId;
		this.subsystemId = subsystemId;
		this.moduleId = moduleId;
		this.processId = processId;
		this.processSequence = processSequence;
	}

	public String getChannelId() {
		return this.channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
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

	public Integer getProcessSequence() {
		return this.processSequence;
	}

	public void setProcessSequence(Integer processSequence) {
		this.processSequence = processSequence;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getChannelId() + ", "
				+ this.getSubsystemId() + ", " + this.getModuleId() + ", "
				+ this.getProcessId() + ", " + this.getProcessSequence() + "]";
	}
}
