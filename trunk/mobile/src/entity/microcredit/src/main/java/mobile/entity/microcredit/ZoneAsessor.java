package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.GeneralEntity;

/**
 * The persistent class for the ZONE_ASESSOR database table. Zones per Assessor
 */
@Entity
@Table(name = "ZONE_ASESSOR")
public class ZoneAsessor extends AbstractEntity implements GeneralEntity {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ZoneAsessorPk pk;

	/**
	 * Observations
	 */
	@Column(name = "OBSERVATIONS", nullable = true)
	private String observations;

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

	public String getObservations() {
		return this.observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
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
		return "ZONE_ASESSOR:[" + this.getPk().toString() + ", " + this.getObservations() + "]";
	}
}
