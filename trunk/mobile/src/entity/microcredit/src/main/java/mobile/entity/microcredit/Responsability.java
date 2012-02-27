package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistorical;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;
import mobile.entity.schema.Historical;

/**
 * The persistent class for the RESPONSABILITY database table. Responsabilities
 * of each partner
 */
@Entity
@Table(name = "RESPONSABILITY")
public class Responsability extends AbstractHistorical implements Multicompany, Multilanguage, Historical {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ResponsabilityPk pk;

	/**
	 * Name
	 */
	@Column(name = "NAME", nullable = false)
	private String name;

	/**
	 * Description
	 */
	@Column(name = "DESCRIPTION", nullable = true)
	private String description;

	public Responsability() {
	}

	public Responsability(ResponsabilityPk pk) {
		this.pk = pk;
	}

	public Responsability(ResponsabilityPk pk, String name) {
		this.pk = pk;
		this.name = name;
	}

	public ResponsabilityPk getPk() {
		return this.pk;
	}

	public void setPk(ResponsabilityPk pk) {
		this.pk = pk;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (ResponsabilityPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Responsability copy = (Responsability) super.clone();
		copy.setPk((ResponsabilityPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "RESPONSABILITY:[" + this.getPk().toString() + ", " + this.getCreated() + ", " + this.getName() + ", "
				+ this.getDescription() + "]";
	}
}
