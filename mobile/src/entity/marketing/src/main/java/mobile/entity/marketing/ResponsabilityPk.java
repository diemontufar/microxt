package mobile.entity.marketing;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;
import mobile.entity.schema.HistoricalKey;

/**
 * The primary key class for the RESPONSABILITY database table.
 */
@Embeddable
public class ResponsabilityPk extends AbstractCompanyLanguageHistoricalKey implements MulticompanyKey,
		MultilanguageKey, HistoricalKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Responsability id
	 */
	@Column(name = "RESPONSABILITY_ID", nullable = false)
	private String responsabilityId;

	public ResponsabilityPk() {
	}

	public ResponsabilityPk(String responsabilityId) {
		this.responsabilityId = responsabilityId;
	}

	public String getResponsabilityId() {
		return this.responsabilityId;
	}

	public void setResponsabilityId(String responsabilityId) {
		this.responsabilityId = responsabilityId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getExpired() + ", "
				+ this.getResponsabilityId() + "]";
	}
}
