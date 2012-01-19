package mobile.entity.zonification;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistorical;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;
import mobile.entity.schema.Historical;

/**
 * The persistent class for the ASESSOR database table. Asessor
 */
@Entity
@Table(name = "ASESSOR")
public class Asessor extends AbstractHistorical implements Multicompany, Multilanguage, Historical {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AsessorPk pk;

	/**
	 * Person id
	 */
	@Column(name = "PERSON_ID", nullable = false)
	private Long personId;

	public Asessor() {
	}

	public Asessor(AsessorPk pk) {
		this.pk = pk;
	}

	public Asessor(AsessorPk pk, Long personId) {
		this.pk = pk;
		this.personId = personId;
	}

	public AsessorPk getPk() {
		return this.pk;
	}

	public void setPk(AsessorPk pk) {
		this.pk = pk;
	}

	public Long getPersonId() {
		return this.personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (AsessorPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Asessor copy = (Asessor) super.clone();
		copy.setPk((AsessorPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "ASESSOR:[" + this.getPk().toString() + ", " + this.getCreated() + ", " + this.getPersonId() + "]";
	}
}
