package mobile.entity.marketing;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;
import mobile.entity.schema.HistoricalKey;

/**
 * The primary key class for the PARTNER database table.
 */
@Embeddable
public class PartnerPk extends AbstractCompanyLanguageHistoricalKey implements MulticompanyKey, MultilanguageKey,
		HistoricalKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Person id
	 */
	@Column(name = "PARTNER_ID", nullable = false)
	private String partnerId;

	public PartnerPk() {
	}

	public PartnerPk(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getExpired() + ", "
				+ this.getPartnerId() + "]";
	}
}
