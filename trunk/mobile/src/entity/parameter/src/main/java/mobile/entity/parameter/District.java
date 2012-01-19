package mobile.entity.parameter;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the DISTRICT database table. Values of districts
 */
@Entity
@Table(name = "DISTRICT")
public class District extends AbstractEntity implements Multicompany, Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DistrictPk pk;

	/**
	 * Name of district
	 */
	@Column(name = "NAME", nullable = false)
	private String name;

	public District() {
	}

	public District(DistrictPk pk) {
		this.pk = pk;
	}

	public District(DistrictPk pk, String name) {
		this.pk = pk;
		this.name = name;
	}

	public DistrictPk getPk() {
		return this.pk;
	}

	public void setPk(DistrictPk pk) {
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
		this.pk = (DistrictPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		District copy = (District) super.clone();
		copy.setPk((DistrictPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "DISTRICT:[" + this.getPk().toString() + ", " + this.getName() + "]";
	}
}
