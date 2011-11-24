package mobile.entity.security;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the PROCESS_ID database table.
 */
@Entity
@Table(name = "PROCESS_ID")
public class ProcessId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProcessIdPk pk;

	public ProcessId() {
	}

	public ProcessId(ProcessIdPk pk) {
		this.pk = pk;
	}

	public ProcessIdPk getPk() {
		return this.pk;
	}

	public void setPk(ProcessIdPk pk) {
		this.pk = pk;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (ProcessIdPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		ProcessId copy = (ProcessId) super.clone();

		copy.setPk((ProcessIdPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "PROCESS_ID:[" + this.getPk().toString() + "]";
	}
}
