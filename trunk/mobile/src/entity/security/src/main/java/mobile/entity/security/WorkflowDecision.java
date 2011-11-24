package mobile.entity.security;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;

/**
 * The persistent class for the WORKFLOW_DECISION database table. Values of
 * workflow decisions
 */
@Entity
@Table(name = "WORKFLOW_DECISION")
public class WorkflowDecision extends AbstractEntity implements Multicompany {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WorkflowDecisionPk pk;

	/**
	 * Decision rule
	 */
	@Column(name = "DECISION_RULE", nullable = false)
	private String decisionRule;

	/**
	 * Workflow step Id
	 */
	@Column(name = "WORKFLOW_STEP_ID", nullable = false)
	private String workflowStepId;

	/**
	 * Channel Id
	 */
	@Column(name = "CHANNEL_ID", nullable = true)
	private String channelId;

	/**
	 * Subsystem Id
	 */
	@Column(name = "SUBSYSTEM_ID", nullable = true)
	private String subsystemId;

	/**
	 * Module Id
	 */
	@Column(name = "MODULE_ID", nullable = true)
	private String moduleId;

	/**
	 * Process Id
	 */
	@Column(name = "PROCESS_ID", nullable = true)
	private String processId;

	/**
	 * Process sequence
	 */
	@Column(name = "PROCESS_SEQUENCE", nullable = true)
	private Integer processSequence;

	public WorkflowDecision() {
	}

	public WorkflowDecision(WorkflowDecisionPk pk) {
		this.pk = pk;
	}

	public WorkflowDecision(WorkflowDecisionPk pk, String decisionRule,
			String workflowStepId) {
		this.pk = pk;
		this.decisionRule = decisionRule;
		this.workflowStepId = workflowStepId;
	}

	public WorkflowDecisionPk getPk() {
		return this.pk;
	}

	public void setPk(WorkflowDecisionPk pk) {
		this.pk = pk;
	}

	public String getDecisionRule() {
		return this.decisionRule;
	}

	public void setDecisionRule(String decisionRule) {
		this.decisionRule = decisionRule;
	}

	public String getWorkflowStepId() {
		return this.workflowStepId;
	}

	public void setWorkflowStepId(String workflowStepId) {
		this.workflowStepId = workflowStepId;
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
	public void setPk(Object pk) {
		this.pk = (WorkflowDecisionPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		WorkflowDecision copy = (WorkflowDecision) super.clone();

		copy.setPk((WorkflowDecisionPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "WORKFLOW_DECISION:[" + this.getPk().toString() + ", "
				+ this.getDecisionRule() + ", " + this.getWorkflowStepId()
				+ ", " + this.getChannelId() + ", " + this.getSubsystemId()
				+ ", " + this.getModuleId() + ", " + this.getProcessId() + ", "
				+ this.getProcessSequence() + "]";
	}
}
