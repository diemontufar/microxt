package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the USER_TYPE database table. Values of user types
 */
@Entity
@Table(name = "USER_TYPE")
public class UserType extends AbstractEntity implements Multicompany,
		Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserTypePk pk;

	/**
	 * Name of user type
	 */
	@Column(name = "NAME", nullable = false)
	private String name;

	public UserType() {
	}

	public UserType(UserTypePk pk) {
		this.pk = pk;
	}

	public UserType(UserTypePk pk, String name) {
		this.pk = pk;
		this.name = name;
	}

	public UserTypePk getPk() {
		return this.pk;
	}

	public void setPk(UserTypePk pk) {
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
		this.pk = (UserTypePk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		UserType copy = (UserType) super.clone();
		copy.setPk((UserTypePk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "USER_TYPE:[" + this.getPk().toString() + ", " + this.getName()
				+ "]";
	}
}
