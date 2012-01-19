package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistorical;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Historical;

/**
 * The persistent class for the USER_NOTIFICATION database table. Values of user
 * notification
 */
@Entity
@Table(name = "USER_NOTIFICATION")
public class UserNotification extends AbstractHistorical implements Multicompany, Historical {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserNotificationPk pk;

	/**
	 * User notification type Id
	 */
	@Column(name = "USER_NOTIFICATION_TYPE_ID", nullable = false)
	private String userNotificationTypeId;

	/**
	 * Subject
	 */
	@Column(name = "SUBJECT", nullable = false)
	private String subject;

	/**
	 * Message
	 */
	@Column(name = "MESSAGE", nullable = false)
	private String message;

	/**
	 * Read
	 */
	@Column(name = "READ_", nullable = false)
	private Boolean read;

	public UserNotification() {
	}

	public UserNotification(UserNotificationPk pk) {
		this.pk = pk;
	}

	public UserNotification(UserNotificationPk pk, String userNotificationTypeId, String subject, String message,
			Boolean read) {
		this.pk = pk;
		this.userNotificationTypeId = userNotificationTypeId;
		this.subject = subject;
		this.message = message;
		this.read = read;
	}

	public UserNotificationPk getPk() {
		return this.pk;
	}

	public void setPk(UserNotificationPk pk) {
		this.pk = pk;
	}

	public String getUserNotificationTypeId() {
		return this.userNotificationTypeId;
	}

	public void setUserNotificationTypeId(String userNotificationTypeId) {
		this.userNotificationTypeId = userNotificationTypeId;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getRead() {
		return this.read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (UserNotificationPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		UserNotification copy = (UserNotification) super.clone();
		copy.setPk((UserNotificationPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "USER_NOTIFICATION:[" + this.getPk().toString() + ", " + this.getCreated() + ", "
				+ this.getUserNotificationTypeId() + ", " + this.getSubject() + ", " + this.getMessage() + ", "
				+ this.getRead() + "]";
	}
}
