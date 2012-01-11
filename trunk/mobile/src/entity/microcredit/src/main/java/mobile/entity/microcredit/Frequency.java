package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the FREQUENCY database table. Frecuency of payments
 */
@Entity
@Table(name = "FREQUENCY")
public class Frequency extends AbstractEntity implements Multicompany,
		Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FrequencyPk pk;

	/**
	 * Description
	 */
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	public Frequency() {
	}

	public Frequency(FrequencyPk pk) {
		this.pk = pk;
	}

	public Frequency(FrequencyPk pk, String description) {
		this.pk = pk;
		this.description = description;
	}

	public FrequencyPk getPk() {
		return this.pk;
	}

	public void setPk(FrequencyPk pk) {
		this.pk = pk;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (FrequencyPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Frequency copy = (Frequency) super.clone();
		copy.setPk((FrequencyPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "FREQUENCY:[" + this.getPk().toString() + ", "
				+ this.getDescription() + "]";
	}
}
