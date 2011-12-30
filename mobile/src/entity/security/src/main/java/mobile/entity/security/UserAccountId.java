package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the USER_ACCOUNT_ID database table.
 */
@Entity
@Table(name = "USER_ACCOUNT_ID")
public class UserAccountId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * User Id
	 */
	@Id
	@Column(name = "USER_ID", nullable = false)
	private String userId;

	public UserAccountId() {
	}

	public UserAccountId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public Object getPk() {
		return this.userId;
	}

	@Override
	public void setPk(Object pk) {
		this.userId = (String) pk;
	}

	@Override
	public String toString() {
		return "USER_ACCOUNT_ID:[" + this.getPk().toString() + "]";
	}
}
