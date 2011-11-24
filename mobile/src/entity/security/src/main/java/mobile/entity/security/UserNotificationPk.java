package mobile.entity.security;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.HistoricalKey;
import mobile.entity.schema.MulticompanyKey;

/**
 * The primary key class for the USER_NOTIFICATION database table.
 */
@Embeddable
public class UserNotificationPk extends AbstractCompanyHistoricalKey implements
		MulticompanyKey, HistoricalKey {
	private static final long serialVersionUID = 1L;

	/**
	 * User Id
	 */
	@Column(name = "USER_ID", nullable = false)
	private String userId;

	public UserNotificationPk() {
	}

	public UserNotificationPk(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getExpired() + ", "
				+ this.getUserId() + "]";
	}
}
