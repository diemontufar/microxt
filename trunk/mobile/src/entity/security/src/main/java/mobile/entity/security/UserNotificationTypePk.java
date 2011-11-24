package mobile.entity.security;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import mobile.entity.schema.AbstractLanguageKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the USER_NOTIFICATION_TYPE database table.
 */
@Embeddable
public class UserNotificationTypePk extends AbstractLanguageKey implements
		MultilanguageKey {
	private static final long serialVersionUID = 1L;

	/**
	 * User notification type Id
	 */
	@Column(name = "USER_NOTIFICATION_TYPE_ID", nullable = false)
	private String userNotificationTypeId;

	public UserNotificationTypePk() {
	}

	public UserNotificationTypePk(String userNotificationTypeId) {
		this.userNotificationTypeId = userNotificationTypeId;
	}

	public String getUserNotificationTypeId() {
		return this.userNotificationTypeId;
	}

	public void setUserNotificationTypeId(String userNotificationTypeId) {
		this.userNotificationTypeId = userNotificationTypeId;
	}

	@Override
	public String toString() {
		return "[" + this.getLanguageId() + ", "
				+ this.getUserNotificationTypeId() + "]";
	}
}
