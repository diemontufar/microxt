package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the SUBSYSTEM database table. Values of subsystems
 */
@Entity
@Table(name = "SUBSYSTEM")
public class Subsystem extends AbstractEntity implements Multicompany,
		Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SubsystemPk pk;

	/**
	 * Name of subsystem
	 */
	@Column(name = "NAME", nullable = false)
	private String name;

	public Subsystem() {
	}

	public Subsystem(SubsystemPk pk) {
		this.pk = pk;
	}

	public Subsystem(SubsystemPk pk, String name) {
		this.pk = pk;
		this.name = name;
	}

	public SubsystemPk getPk() {
		return this.pk;
	}

	public void setPk(SubsystemPk pk) {
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
		this.pk = (SubsystemPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Subsystem copy = (Subsystem) super.clone();
		copy.setPk((SubsystemPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "SUBSYSTEM:[" + this.getPk().toString() + ", " + this.getName()
				+ "]";
	}
}
