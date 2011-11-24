package mobile.entity.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the USER_STATUS_ID database table.
 */
@Entity
@Table(name = "USER_STATUS_ID")
public class UserStatusId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * User status Id
	 */
	@Id
	@Column(name = "USER_STATUS_ID", nullable = false)
	private String userStatusId;

	public UserStatusId() {
	}

	public UserStatusId(String userStatusId) {
		this.userStatusId = userStatusId;
	}

	public String getUserStatusId() {
		return this.userStatusId;
	}

	public void setUserStatusId(String userStatusId) {
		this.userStatusId = userStatusId;
	}

	@Override
	public Object getPk() {
		return this.userStatusId;
	}

	@Override
	public void setPk(Object pk) {
		this.userStatusId = (String) pk;
	}

	@Override
	public String toString() {
		return "USER_STATUS_ID:[" + this.getPk().toString() + "]";
	}
}
