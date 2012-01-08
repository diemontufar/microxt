package mobile.entity.zonification;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the ASESSOR_ID database table.
 */
@Entity
@Table(name = "ASESSOR_ID")
public class AsessorId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Assessor
	 */
	@Id
	@Column(name = "ASESSOR_ID", nullable = false)
	private String asessorId;

	public AsessorId() {
	}

	public AsessorId(String asessorId) {
		this.asessorId = asessorId;
	}

	public String getAsessorId() {
		return this.asessorId;
	}

	public void setAsessorId(String asessorId) {
		this.asessorId = asessorId;
	}

	@Override
	public Object getPk() {
		return this.asessorId;
	}

	@Override
	public void setPk(Object pk) {
		this.asessorId = (String) pk;
	}

	@Override
	public String toString() {
		return "ASESSOR_ID:[" + this.getPk().toString() + "]";
	}
}
