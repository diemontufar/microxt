package mobile.entity.marketing;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the PARTNER_GROUP_MEMBER_ID database table.
 */
@Entity
@Table(name = "PARTNER_GROUP_MEMBER_ID")
public class PartnerGroupMemberId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Group member id
	 */
	@Id
	@Column(name = "PARTNER_GROUP_MEMBER_ID", nullable = false)
	private String partnerGroupMemberId;

	public PartnerGroupMemberId() {
	}

	public PartnerGroupMemberId(String partnerGroupMemberId) {
		this.partnerGroupMemberId = partnerGroupMemberId;
	}

	public String getPartnerGroupMemberId() {
		return this.partnerGroupMemberId;
	}

	public void setPartnerGroupMemberId(String partnerGroupMemberId) {
		this.partnerGroupMemberId = partnerGroupMemberId;
	}

	@Override
	public Object getPk() {
		return this.partnerGroupMemberId;
	}

	@Override
	public void setPk(Object pk) {
		this.partnerGroupMemberId = (String) pk;
	}

	@Override
	public String toString() {
		return "PARTNER_GROUP_MEMBER_ID:[" + this.getPk().toString() + "]";
	}
}
