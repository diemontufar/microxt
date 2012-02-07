package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;
import mobile.entity.schema.HistoricalKey;
import mobile.entity.schema.SequentialKey;

/**
 * The primary key class for the PARTNER database table.
 */
@Embeddable
public class PartnerPk extends AbstractCompanyLanguageHistoricalKey implements
		MulticompanyKey, MultilanguageKey, HistoricalKey, SequentialKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Person id
	 */
	@Column(name = "PARTNER_ID", nullable = false)
	private Integer partnerId;

	public PartnerPk() {
	}

	public PartnerPk(Integer partnerId) {
		this.partnerId = partnerId;
	}

	public Integer getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}

	@Override
	public Integer getId() {
		return this.partnerId;
	}

	@Override
	public void setId(Integer sequentialNumber) {
		this.partnerId = sequentialNumber;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", "
				+ this.getExpired() + ", " + this.getPartnerId() + "]";
	}
}
