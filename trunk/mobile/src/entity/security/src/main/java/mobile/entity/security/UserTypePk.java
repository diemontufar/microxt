package mobile.entity.security;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the USER_TYPE database table.
 */
@Embeddable
public class UserTypePk extends AbstractCompanyLanguageKey implements
		MulticompanyKey, MultilanguageKey {
	private static final long serialVersionUID = 1L;

	/**
	 * User type Id
	 */
	@Column(name = "USER_TYPE_ID", nullable = false)
	private String userTypeId;

	public UserTypePk() {
	}

	public UserTypePk(String userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", "
				+ this.getUserTypeId() + "]";
	}
}
