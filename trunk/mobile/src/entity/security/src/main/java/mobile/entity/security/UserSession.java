package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistoricalLocking;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Historical;
import mobile.entity.schema.OptimisticLocking;

/**
 * The persistent class for the USER_SESSION database table. Values of user
 * sessions
 */
@Entity
@Table(name = "USER_SESSION")
public class UserSession extends AbstractHistoricalLocking implements Multicompany, Historical, OptimisticLocking {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserSessionPk pk;

	/**
	 * Token
	 */
	@Column(name = "SESSION_ID", nullable = false)
	private String sessionId;

	public UserSession() {
	}

	public UserSession(UserSessionPk pk) {
		this.pk = pk;
	}

	public UserSession(UserSessionPk pk, String sessionId) {
		this.pk = pk;
		this.sessionId = sessionId;
	}

	public UserSessionPk getPk() {
		return this.pk;
	}

	public void setPk(UserSessionPk pk) {
		this.pk = pk;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (UserSessionPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		UserSession copy = (UserSession) super.clone();
		copy.setPk((UserSessionPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "USER_SESSION:[" + this.getPk().toString() + ", " + this.getCreated() + ", " + this.getSessionId()
				+ ", " + this.getVersion() + "]";
	}
}
