package mobile.entity.parameter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the DEPARTMENT_ID database table.
 */
@Entity
@Table(name = "DEPARTMENT_ID")
public class DepartmentId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DepartmentIdPk pk;

	public DepartmentId() {
	}

	public DepartmentId(DepartmentIdPk pk) {
		this.pk = pk;
	}

	public DepartmentIdPk getPk() {
		return this.pk;
	}

	public void setPk(DepartmentIdPk pk) {
		this.pk = pk;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (DepartmentIdPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		DepartmentId copy = (DepartmentId) super.clone();

		copy.setPk((DepartmentIdPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "DEPARTMENT_ID:[" + this.getPk().toString() + "]";
	}
}
