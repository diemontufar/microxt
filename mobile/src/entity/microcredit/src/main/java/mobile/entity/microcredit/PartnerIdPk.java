package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityKey;
import mobile.entity.schema.GeneralEntityKey;

/**
 * The primary key class for the PARTNER_ID database table.
 */
@Embeddable
public class PartnerIdPk extends AbstractEntityKey implements GeneralEntityKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Person id
	 */
	@Column(name = "PARTNER_ID", nullable = false)
	private String partnerId;

	/**
	 * User Id
	 */
	@Column(name = "USER_ID", nullable = false)
	private String userId;

	public PartnerIdPk() {
	}

	public PartnerIdPk(String partnerId, String userId) {
		this.partnerId = partnerId;
		this.userId = userId;
	}

	public String getPartnerId() {
		return this.partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "[" + this.getPartnerId() + ", " + this.getUserId() + "]";
	}
}
