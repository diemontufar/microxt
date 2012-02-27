package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the ACCOUNT_STATUS database table. Account status
 */
@Entity
@Table(name = "ACCOUNT_STATUS")
public class AccountStatus extends AbstractEntity implements Multicompany, Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AccountStatusPk pk;

	/**
	 * Description
	 */
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	public AccountStatus() {
	}

	public AccountStatus(AccountStatusPk pk) {
		this.pk = pk;
	}

	public AccountStatus(AccountStatusPk pk, String description) {
		this.pk = pk;
		this.description = description;
	}

	public AccountStatusPk getPk() {
		return this.pk;
	}

	public void setPk(AccountStatusPk pk) {
		this.pk = pk;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (AccountStatusPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		AccountStatus copy = (AccountStatus) super.clone();
		copy.setPk((AccountStatusPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "ACCOUNT_STATUS:[" + this.getPk().toString() + ", " + this.getDescription() + "]";
	}
}
