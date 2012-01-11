package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the QUOTA_TYPE database table.
 */
@Embeddable
public class QuotaTypePk extends AbstractCompanyLanguageKey implements
		MulticompanyKey, MultilanguageKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Fee type
	 */
	@Column(name = "QUOTA_TYPE_ID", nullable = false)
	private String quotaTypeId;

	public QuotaTypePk() {
	}

	public QuotaTypePk(String quotaTypeId) {
		this.quotaTypeId = quotaTypeId;
	}

	public String getQuotaTypeId() {
		return this.quotaTypeId;
	}

	public void setQuotaTypeId(String quotaTypeId) {
		this.quotaTypeId = quotaTypeId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", "
				+ this.getQuotaTypeId() + "]";
	}
}
