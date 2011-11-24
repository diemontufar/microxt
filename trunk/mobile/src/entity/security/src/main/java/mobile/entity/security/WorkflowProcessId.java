package mobile.entity.security;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the WORKFLOW_PROCESS_ID database table.
 */
@Entity
@Table(name = "WORKFLOW_PROCESS_ID")
public class WorkflowProcessId extends AbstractEntityId implements
		GeneralEntityId {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WorkflowProcessIdPk pk;

	public WorkflowProcessId() {
	}

	public WorkflowProcessId(WorkflowProcessIdPk pk) {
		this.pk = pk;
	}

	public WorkflowProcessIdPk getPk() {
		return this.pk;
	}

	public void setPk(WorkflowProcessIdPk pk) {
		this.pk = pk;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (WorkflowProcessIdPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		WorkflowProcessId copy = (WorkflowProcessId) super.clone();

		copy.setPk((WorkflowProcessIdPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "WORKFLOW_PROCESS_ID:[" + this.getPk().toString() + "]";
	}
}
