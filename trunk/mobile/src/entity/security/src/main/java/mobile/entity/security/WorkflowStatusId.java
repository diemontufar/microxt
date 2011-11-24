package mobile.entity.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the WORKFLOW_STATUS_ID database table.
 */
@Entity
@Table(name = "WORKFLOW_STATUS_ID")
public class WorkflowStatusId extends AbstractEntityId implements
		GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Workflow status Id
	 */
	@Id
	@Column(name = "WORKFLOW_STATUS_ID", nullable = false)
	private String workflowStatusId;

	public WorkflowStatusId() {
	}

	public WorkflowStatusId(String workflowStatusId) {
		this.workflowStatusId = workflowStatusId;
	}

	public String getWorkflowStatusId() {
		return this.workflowStatusId;
	}

	public void setWorkflowStatusId(String workflowStatusId) {
		this.workflowStatusId = workflowStatusId;
	}

	@Override
	public Object getPk() {
		return this.workflowStatusId;
	}

	@Override
	public void setPk(Object pk) {
		this.workflowStatusId = (String) pk;
	}

	@Override
	public String toString() {
		return "WORKFLOW_STATUS_ID:[" + this.getPk().toString() + "]";
	}
}
