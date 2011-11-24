package mobile.entity.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the WORKFLOW_STEP_ID database table.
 */
@Entity
@Table(name = "WORKFLOW_STEP_ID")
public class WorkflowStepId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Workflow step Id
	 */
	@Id
	@Column(name = "WORKFLOW_STEP_ID", nullable = false)
	private String workflowStepId;

	public WorkflowStepId() {
	}

	public WorkflowStepId(String workflowStepId) {
		this.workflowStepId = workflowStepId;
	}

	public String getWorkflowStepId() {
		return this.workflowStepId;
	}

	public void setWorkflowStepId(String workflowStepId) {
		this.workflowStepId = workflowStepId;
	}

	@Override
	public Object getPk() {
		return this.workflowStepId;
	}

	@Override
	public void setPk(Object pk) {
		this.workflowStepId = (String) pk;
	}

	@Override
	public String toString() {
		return "WORKFLOW_STEP_ID:[" + this.getPk().toString() + "]";
	}
}
