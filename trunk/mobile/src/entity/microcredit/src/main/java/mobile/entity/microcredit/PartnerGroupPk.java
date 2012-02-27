package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;
import mobile.entity.schema.HistoricalKey;
import mobile.entity.schema.SequentialKey;

/**
 * The primary key class for the PARTNER_GROUP database table.
 */
@Embeddable
public class PartnerGroupPk extends AbstractCompanyLanguageHistoricalKey implements MulticompanyKey, MultilanguageKey,
		HistoricalKey, SequentialKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Partner group id
	 */
	@Column(name = "PARTNER_GROUP_ID", nullable = false)
	private Integer partnerGroupId;

	public PartnerGroupPk() {
	}

	public PartnerGroupPk(Integer partnerGroupId) {
		this.partnerGroupId = partnerGroupId;
	}

	public Integer getPartnerGroupId() {
		return this.partnerGroupId;
	}

	public void setPartnerGroupId(Integer partnerGroupId) {
		this.partnerGroupId = partnerGroupId;
	}

	@Override
	public Integer getId() {
		return this.partnerGroupId;
	}

	@Override
	public void setId(Integer sequentialNumber) {
		this.partnerGroupId = sequentialNumber;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getExpired() + ", "
				+ this.getPartnerGroupId() + "]";
	}
}
