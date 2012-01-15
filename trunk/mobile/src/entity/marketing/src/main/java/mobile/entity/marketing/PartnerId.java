package mobile.entity.marketing;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the PARTNER_ID database table.
 */
@Entity
@Table(name = "PARTNER_ID")
public class PartnerId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Person id
	 */
	@Id
	@Column(name = "PARTNER_ID", nullable = false)
	private String partnerId;

	public PartnerId() {
	}

	public PartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	@Override
	public Object getPk() {
		return this.partnerId;
	}

	@Override
	public void setPk(Object pk) {
		this.partnerId = (String) pk;
	}

	@Override
	public String toString() {
		return "PARTNER_ID:[" + this.getPk().toString() + "]";
	}
}
