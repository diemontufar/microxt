package mobile.entity.marketing;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;
import mobile.entity.schema.HistoricalKey;

/**
* The primary key class for the PARTNER_GROUP database table.
*/
@Embeddable
public class PartnerGroupPk extends AbstractCompanyLanguageHistoricalKey implements MulticompanyKey, MultilanguageKey, HistoricalKey{
private static final long serialVersionUID = 1L;

/**
* Partner group id
*/
@Column(name="PARTNER_GROUP_ID", nullable=false)
private String partnerGroupId;

public PartnerGroupPk() {
}
public PartnerGroupPk(String partnerGroupId) {
this.partnerGroupId = partnerGroupId;
}
public String getPartnerGroupId() {
return this.partnerGroupId;
}
public void setPartnerGroupId(String partnerGroupId) {
this.partnerGroupId = partnerGroupId;
}

@Override
public String toString() {
return "[" +
this.getCompanyId() + ", " +
this.getLanguageId() + ", " +
this.getExpired() + ", " +
this.getPartnerGroupId() + "]";
}
}
