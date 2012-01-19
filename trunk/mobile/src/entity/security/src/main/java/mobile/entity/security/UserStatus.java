package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the USER_STATUS database table. Values of user
 * status
 */
@Entity
@Table(name = "USER_STATUS")
public class UserStatus extends AbstractEntity implements Multicompany, Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserStatusPk pk;

	/**
	 * Name of user status
	 */
	@Column(name = "NAME", nullable = false)
	private String name;

	public UserStatus() {
	}

	public UserStatus(UserStatusPk pk) {
		this.pk = pk;
	}

	public UserStatus(UserStatusPk pk, String name) {
		this.pk = pk;
		this.name = name;
	}

	public UserStatusPk getPk() {
		return this.pk;
	}

	public void setPk(UserStatusPk pk) {
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
		this.pk = (UserStatusPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		UserStatus copy = (UserStatus) super.clone();
		copy.setPk((UserStatusPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "USER_STATUS:[" + this.getPk().toString() + ", " + this.getName() + "]";
	}
}
