package mobile.entity.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the USER_TYPE_ID database table.
 */
@Entity
@Table(name = "USER_TYPE_ID")
public class UserTypeId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * User type Id
	 */
	@Id
	@Column(name = "USER_TYPE_ID", nullable = false)
	private String userTypeId;

	public UserTypeId() {
	}

	public UserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}

	@Override
	public Object getPk() {
		return this.userTypeId;
	}

	@Override
	public void setPk(Object pk) {
		this.userTypeId = (String) pk;
	}

	@Override
	public String toString() {
		return "USER_TYPE_ID:[" + this.getPk().toString() + "]";
	}
}
