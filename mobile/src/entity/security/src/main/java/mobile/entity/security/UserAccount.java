package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistoricalLocking;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Historical;
import mobile.entity.schema.OptimisticLocking;

/**
 * The persistent class for the USER_ACCOUNT database table. Values of user
 * accounts
 */
@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount extends AbstractHistoricalLocking implements
		Multicompany, Historical, OptimisticLocking {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserAccountPk pk;

	/**
	 * Name of user
	 */
	@Column(name = "NAME", nullable = false)
	private String name;

	/**
	 * User type Id
	 */
	@Column(name = "USER_TYPE_ID", nullable = false)
	private String userTypeId;

	/**
	 * User status Id
	 */
	@Column(name = "USER_STATUS_ID", nullable = false)
	private String userStatusId;

	/**
	 * Language Id
	 */
	@Column(name = "LANGUAGE_ID", nullable = false)
	private String languageId;

	/**
	 * Email
	 */
	@Column(name = "EMAIL", nullable = false)
	private String email;

	/**
	 * Person Id
	 */
	@Column(name = "PERSON_ID", nullable = true)
	private Long personId;

	public UserAccount() {
	}

	public UserAccount(UserAccountPk pk) {
		this.pk = pk;
	}

	public UserAccount(UserAccountPk pk, String name, String userTypeId,
			String userStatusId, String languageId, String email) {
		this.pk = pk;
		this.name = name;
		this.userTypeId = userTypeId;
		this.userStatusId = userStatusId;
		this.languageId = languageId;
		this.email = email;
	}

	public UserAccountPk getPk() {
		return this.pk;
	}

	public void setPk(UserAccountPk pk) {
		this.pk = pk;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserStatusId() {
		return this.userStatusId;
	}

	public void setUserStatusId(String userStatusId) {
		this.userStatusId = userStatusId;
	}

	public String getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPersonId() {
		return this.personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (UserAccountPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		UserAccount copy = (UserAccount) super.clone();
		copy.setPk((UserAccountPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "USER_ACCOUNT:[" + this.getPk().toString() + ", "
				+ this.getCreated() + ", " + this.getName() + ", "
				+ this.getUserTypeId() + ", " + this.getUserStatusId() + ", "
				+ this.getLanguageId() + ", " + this.getEmail() + ", "
				+ this.getPersonId() + ", " + this.getVersion() + "]";
	}
}
