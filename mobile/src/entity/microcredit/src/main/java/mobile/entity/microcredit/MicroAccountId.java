package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the MICRO_ACCOUNT_ID database table.
 */
@Entity
@Table(name = "MICRO_ACCOUNT_ID")
public class MicroAccountId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Account number and id
	 */
	@Id
	@Column(name = "ACCOUNT_ID", nullable = false)
	private String accountId;

	public MicroAccountId() {
	}

	public MicroAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountId() {
		return this.accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Override
	public Object getPk() {
		return this.accountId;
	}

	@Override
	public void setPk(Object pk) {
		this.accountId = (String) pk;
	}

	@Override
	public String toString() {
		return "MICRO_ACCOUNT_ID:[" + this.getPk().toString() + "]";
	}
}
