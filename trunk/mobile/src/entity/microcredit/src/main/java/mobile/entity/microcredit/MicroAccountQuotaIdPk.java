package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityKey;
import mobile.entity.schema.GeneralEntityKey;

/**
 * The primary key class for the MICRO_ACCOUNT_QUOTA_ID database table.
 */
@Embeddable
public class MicroAccountQuotaIdPk extends AbstractEntityKey implements GeneralEntityKey {
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

	public MicroAccountQuotaIdPk() {
	}

	public MicroAccountQuotaIdPk(String accountId, Integer subaccount) {
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
		return "[" + this.getAccountId() + ", " + this.getSubaccount() + "]";
	}
}
