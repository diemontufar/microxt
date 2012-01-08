package mobile.entity.zonification;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the ZONE_ASESSOR_ID database table.
 */
@Entity
@Table(name = "ZONE_ASESSOR_ID")
public class ZoneAsessorId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ZoneAsessorIdPk pk;

	public ZoneAsessorId() {
	}

	public ZoneAsessorId(ZoneAsessorIdPk pk) {
		this.pk = pk;
	}

	public ZoneAsessorIdPk getPk() {
		return this.pk;
	}

	public void setPk(ZoneAsessorIdPk pk) {
		this.pk = pk;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (ZoneAsessorIdPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		ZoneAsessorId copy = (ZoneAsessorId) super.clone();
		copy.setPk((ZoneAsessorIdPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "ZONE_ASESSOR_ID:[" + this.getPk().toString() + "]";
	}
}
