package mobile.entity.microcredit;

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
	private Integer partnerId;

	public PartnerId() {
	}

	public PartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}

	public Integer getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}

	@Override
	public Object getPk() {
		return this.partnerId;
	}

	@Override
	public void setPk(Object pk) {
		this.partnerId = (Integer) pk;
	}

	@Override
	public String toString() {
		return "PARTNER_ID:[" + this.getPk().toString() + "]";
	}
}
