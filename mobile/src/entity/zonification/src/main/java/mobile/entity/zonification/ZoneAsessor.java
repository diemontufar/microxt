package mobile.entity.zonification;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the ZONE_ASESSOR database table. Zones per Asessor
 */
@Entity
@Table(name = "ZONE_ASESSOR")
public class ZoneAsessor extends AbstractEntity implements Multicompany,
		Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ZoneAsessorPk pk;

	public ZoneAsessor() {
	}

	public ZoneAsessor(ZoneAsessorPk pk) {
		this.pk = pk;
	}

	public ZoneAsessorPk getPk() {
		return this.pk;
	}

	public void setPk(ZoneAsessorPk pk) {
		this.pk = pk;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (ZoneAsessorPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		ZoneAsessor copy = (ZoneAsessor) super.clone();
		copy.setPk((ZoneAsessorPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "ZONE_ASESSOR:[" + this.getPk().toString() + "]";
	}
}