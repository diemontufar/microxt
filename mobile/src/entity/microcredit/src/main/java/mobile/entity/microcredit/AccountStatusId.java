package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the ACCOUNT_STATUS_ID database table.
 */
@Entity
@Table(name = "ACCOUNT_STATUS_ID")
public class AccountStatusId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Status
	 */
	@Id
	@Column(name = "STATUS_ID", nullable = false)
	private String statusId;

	public AccountStatusId() {
	}

	public AccountStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getStatusId() {
		return this.statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	@Override
	public Object getPk() {
		return this.statusId;
	}

	@Override
	public void setPk(Object pk) {
		this.statusId = (String) pk;
	}

	@Override
	public String toString() {
		return "ACCOUNT_STATUS_ID:[" + this.getPk().toString() + "]";
	}
}
