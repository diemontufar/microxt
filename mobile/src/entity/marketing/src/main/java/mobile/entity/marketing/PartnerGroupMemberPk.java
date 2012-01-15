package mobile.entity.marketing;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.HistoricalKey;

/**
* The primary key class for the PARTNER_GROUP_MEMBER database table.
*/
@Embeddable
public class PartnerGroupMemberPk extends AbstractCompanyHistoricalKey implements MulticompanyKey, HistoricalKey{
private static final long serialVersionUID = 1L;

/**
* Group member id
*/
@Column(name="PARTNER_GROUP_MEMBER_ID", nullable=false)
private String partnerGroupMemberId;

public PartnerGroupMemberPk() {
}
public PartnerGroupMemberPk(String partnerGroupMemberId) {
this.partnerGroupMemberId = partnerGroupMemberId;
}
public String getPartnerGroupMemberId() {
return this.partnerGroupMemberId;
}
public void setPartnerGroupMemberId(String partnerGroupMemberId) {
this.partnerGroupMemberId = partnerGroupMemberId;
}

@Override
public String toString() {
return "[" +
this.getCompanyId() + ", " +
this.getExpired() + ", " +
this.getPartnerGroupMemberId() + "]";
}
}
