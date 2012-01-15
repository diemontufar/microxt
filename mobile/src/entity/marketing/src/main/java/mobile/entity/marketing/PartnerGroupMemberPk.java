package mobile.entity.marketing;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.HistoricalKey;

/**
 * The primary key class for the PARTNER_GROUP_MEMBER database table.
 */
@Embeddable
public class PartnerGroupMemberPk extends AbstractCompanyHistoricalKey
		implements MulticompanyKey, HistoricalKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Group member id
	 */
	@Column(name = "PARTNER_GROUP_MEMBERS_ID", nullable = false)
	private String partnerGroupMembersId;

	public PartnerGroupMemberPk() {
	}

	public PartnerGroupMemberPk(String partnerGroupMembersId) {
		this.partnerGroupMembersId = partnerGroupMembersId;
	}

	public String getPartnerGroupMembersId() {
		return this.partnerGroupMembersId;
	}

	public void setPartnerGroupMembersId(String partnerGroupMembersId) {
		this.partnerGroupMembersId = partnerGroupMembersId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getExpired() + ", "
				+ this.getPartnerGroupMembersId() + "]";
	}
}
