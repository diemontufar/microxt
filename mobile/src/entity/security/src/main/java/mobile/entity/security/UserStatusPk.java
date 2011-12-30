package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the USER_STATUS database table.
 */
@Embeddable
public class UserStatusPk extends AbstractCompanyLanguageKey implements
		MulticompanyKey, MultilanguageKey {
	private static final long serialVersionUID = 1L;

	/**
	 * User status Id
	 */
	@Column(name = "USER_STATUS_ID", nullable = false)
	private String userStatusId;

	public UserStatusPk() {
	}

	public UserStatusPk(String userStatusId) {
		this.userStatusId = userStatusId;
	}

	public String getUserStatusId() {
		return this.userStatusId;
	}

	public void setUserStatusId(String userStatusId) {
		this.userStatusId = userStatusId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", "
				+ this.getUserStatusId() + "]";
	}
}
