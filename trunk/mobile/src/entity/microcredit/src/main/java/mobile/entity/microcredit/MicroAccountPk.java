package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.HistoricalKey;

/**
 * The primary key class for the MICRO_ACCOUNT database table.
 */
@Embeddable
public class MicroAccountPk extends AbstractCompanyHistoricalKey implements MulticompanyKey, HistoricalKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Account number and id
	 */
	@Column(name = "ACCOUNT_ID", nullable = false)
	private String accountId;

	public MicroAccountPk() {
	}

	public MicroAccountPk(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getExpired() + ", " + this.getAccountId() + "]";
	}
}
