package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.HistoricalKey;

/**
 * The primary key class for the MICRO_ACCOUNT_QUOTA database table.
 */
@Embeddable
public class MicroAccountQuotaPk extends AbstractCompanyHistoricalKey implements MulticompanyKey, HistoricalKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Account number and id
	 */
	@Column(name = "ACCOUNT_ID", nullable = false)
	private String accountId;

	/**
	 * Quota number
	 */
	@Column(name = "SUBACCOUNT", nullable = false)
	private Integer subaccount;

	public MicroAccountQuotaPk() {
	}

	public MicroAccountQuotaPk(String accountId, Integer subaccount) {
		this.accountId = accountId;
		this.subaccount = subaccount;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Integer getSubaccount() {
		return this.subaccount;
	}

	public void setSubaccount(Integer subaccount) {
		this.subaccount = subaccount;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getExpired() + ", " + this.getAccountId() + ", "
				+ this.getSubaccount() + "]";
	}
}
