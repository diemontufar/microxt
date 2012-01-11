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
	@Column(name = "FRECUENCY_ID", nullable = false)
	private String frecuencyId;

	public FrequencyId() {
	}

	public FrequencyId(String frecuencyId) {
		this.frecuencyId = frecuencyId;
	}

	public String getFrecuencyId() {
		return this.frecuencyId;
	}

	public void setFrecuencyId(String frecuencyId) {
		this.frecuencyId = frecuencyId;
	}

	@Override
	public Object getPk() {
		return this.frecuencyId;
	}

	@Override
	public void setPk(Object pk) {
		this.frecuencyId = (String) pk;
	}

	@Override
	public String toString() {
		return "FREQUENCY_ID:[" + this.getPk().toString() + "]";
	}
}
