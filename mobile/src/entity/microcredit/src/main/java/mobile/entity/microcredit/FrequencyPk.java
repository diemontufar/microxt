package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the FREQUENCY database table.
 */
@Embeddable
public class FrequencyPk extends AbstractCompanyLanguageKey implements
		MulticompanyKey, MultilanguageKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Frecuency
	 */
	@Column(name = "FRECUENCY_ID", nullable = false)
	private String frecuencyId;

	public FrequencyPk() {
	}

	public FrequencyPk(String frecuencyId) {
		this.frecuencyId = frecuencyId;
	}

	public String getFrecuencyId() {
		return this.frecuencyId;
	}

	public void setFrecuencyId(String frecuencyId) {
		this.frecuencyId = frecuencyId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", "
				+ this.getFrecuencyId() + "]";
	}
}
