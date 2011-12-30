package mobile.entity.security;

import java.sql.Date;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistorical;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Historical;

/**
 * The persistent class for the USER_ACCESS database table. Values of user
 * access
 */
@Entity
@Table(name = "USER_ACCESS")
public class UserAccess extends AbstractHistorical implements Multicompany,
		Historical {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserAccessPk pk;

	/**
	 * User Key
	 */
	@Column(name = "USER_KEY", nullable = false)
	private String userKey;

	/**
	 * Last change
	 */
	@Column(name = "LAST_CHANGE", nullable = false)
	private Date lastChange;

	/**
	 * Question
	 */
	@Column(name = "QUESTION", nullable = true)
	private String question;

	/**
	 * Answer
	 */
	@Column(name = "ANSWER", nullable = true)
	private String answer;

	public UserAccess() {
	}

	public UserAccess(UserAccessPk pk) {
		this.pk = pk;
	}

	public UserAccess(UserAccessPk pk, String userKey, Date lastChange) {
		this.pk = pk;
		this.userKey = userKey;
		this.lastChange = lastChange;
	}

	public UserAccessPk getPk() {
		return this.pk;
	}

	public void setPk(UserAccessPk pk) {
		this.pk = pk;
	}

	public String getUserKey() {
		return this.userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public Date getLastChange() {
		return this.lastChange;
	}

	public void setLastChange(Date lastChange) {
		this.lastChange = lastChange;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (UserAccessPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		UserAccess copy = (UserAccess) super.clone();
		copy.setPk((UserAccessPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "USER_ACCESS:[" + this.getPk().toString() + ", "
				+ this.getCreated() + ", " + this.getUserKey() + ", "
				+ this.getLastChange() + ", " + this.getQuestion() + ", "
				+ this.getAnswer() + "]";
	}
}
