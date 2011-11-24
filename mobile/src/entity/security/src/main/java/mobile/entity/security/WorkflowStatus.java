package mobile.entity.security;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the WORKFLOW_STATUS database table. Values of
 * workflow status
 */
@Entity
@Table(name = "WORKFLOW_STATUS")
public class WorkflowStatus extends AbstractEntity implements Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private WorkflowStatusPk pk;

	/**
	 * Name of workflow step
	 */
	@Column(name = "NAME", nullable = false)
	private String name;

	public WorkflowStatus() {
	}

	public WorkflowStatus(WorkflowStatusPk pk) {
		this.pk = pk;
	}

	public WorkflowStatus(WorkflowStatusPk pk, String name) {
		this.pk = pk;
		this.name = name;
	}

	public WorkflowStatusPk getPk() {
		return this.pk;
	}

	public void setPk(WorkflowStatusPk pk) {
		this.pk = pk;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (WorkflowStatusPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		WorkflowStatus copy = (WorkflowStatus) super.clone();

		copy.setPk((WorkflowStatusPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "WORKFLOW_STATUS:[" + this.getPk().toString() + ", "
				+ this.getName() + "]";
	}
}
