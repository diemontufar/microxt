package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the FREQUENCY database table.
 */
@Embeddable
public class FrequencyPk extends AbstractCompanyLanguageKey implements MulticompanyKey, MultilanguageKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Frecuency
	 */
	@Column(name = "FREQUENCY_ID", nullable = false)
	private String frequencyId;

	public FrequencyPk() {
	}

	public FrequencyPk(String frequencyId) {
		this.frequencyId = frequencyId;
	}

	public String getFrequencyId() {
		return this.frequencyId;
	}

	public void setFrequencyId(String frequencyId) {
		this.frequencyId = frequencyId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getFrequencyId() + "]";
	}
}
