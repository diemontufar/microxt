package mobile.entity.security;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mobile.entity.schema.AbstractHistorical;
import mobile.entity.schema.Historical;
import mobile.entity.schema.Multicompany;

/**
 * The persistent class for the USER_AUTHORIZATION database table. Values of
 * user authorizations
 */
@Entity
@Table(name = "USER_AUTHORIZATION")
public class UserAuthorization extends AbstractHistorical implements
		Multicompany, Historical {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserAuthorizationPk pk;

	public UserAuthorization() {
	}

	public UserAuthorization(UserAuthorizationPk pk) {
		this.pk = pk;
	}

	public UserAuthorizationPk getPk() {
		return this.pk;
	}

	public void setPk(UserAuthorizationPk pk) {
		this.pk = pk;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (UserAuthorizationPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		UserAuthorization copy = (UserAuthorization) super.clone();

		copy.setPk((UserAuthorizationPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "USER_AUTHORIZATION:[" + this.getPk().toString() + ", "
				+ this.getCreated() + "]";
	}
}
