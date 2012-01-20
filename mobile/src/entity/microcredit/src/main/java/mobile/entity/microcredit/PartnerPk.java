package mobile.entity.microcredit;

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

	/**
	 * User Id
	 */
	@Column(name = "USER_ID", nullable = false)
	private String userId;

	public PartnerPk() {
	}

	public PartnerPk(String partnerId, String userId) {
		this.partnerId = partnerId;
		this.userId = userId;
	}

	public String getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getExpired() + ", "
				+ this.getPartnerId() + ", " + this.getUserId() + "]";
	}
}
