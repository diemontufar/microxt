package mobile.entity.security;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the USER_NOTIFICATION_TYPE database table. Values of
 * user notification types
 */
@Entity
@Table(name = "USER_NOTIFICATION_TYPE")
public class UserNotificationType extends AbstractEntity implements
		Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserNotificationTypePk pk;

	/**
	 * Name of user notification type
	 */
	@Column(name = "NAME", nullable = false)
	private String name;

	public UserNotificationType() {
	}

	public UserNotificationType(UserNotificationTypePk pk) {
		this.pk = pk;
	}

	public UserNotificationType(UserNotificationTypePk pk, String name) {
		this.pk = pk;
		this.name = name;
	}

	public UserNotificationTypePk getPk() {
		return this.pk;
	}

	public void setPk(UserNotificationTypePk pk) {
		this.pk = pk;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (UserNotificationTypePk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		UserNotificationType copy = (UserNotificationType) super.clone();

		copy.setPk((UserNotificationTypePk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "USER_NOTIFICATION_TYPE:[" + this.getPk().toString() + ", "
				+ this.getName() + "]";
	}
}
