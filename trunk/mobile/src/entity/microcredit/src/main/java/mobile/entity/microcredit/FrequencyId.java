package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the FREQUENCY_ID database table.
 */
@Entity
@Table(name = "FREQUENCY_ID")
public class FrequencyId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Frecuency
	 */
	@Id
	@Column(name = "FREQUENCY_ID", nullable = false)
	private String frequencyId;

	public FrequencyId() {
	}

	public FrequencyId(String frequencyId) {
		this.frequencyId = frequencyId;
	}

	public String getFrequencyId() {
		return this.frequencyId;
	}

	public void setFrequencyId(String frequencyId) {
		this.frequencyId = frequencyId;
	}

	@Override
	public Object getPk() {
		return this.frequencyId;
	}

	@Override
	public void setPk(Object pk) {
		this.frequencyId = (String) pk;
	}

	@Override
	public String toString() {
		return "FREQUENCY_ID:[" + this.getPk().toString() + "]";
	}
}
