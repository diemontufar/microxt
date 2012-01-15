package mobile.entity.marketing;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistoricalLocking;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Historical;
import mobile.entity.schema.OptimisticLocking;

/**
* The persistent class for the PARTNER_GROUP_MEMBER database table.
* Group Members
*/
@Entity
@Table(name="PARTNER_GROUP_MEMBER")
public class PartnerGroupMember extends AbstractHistoricalLocking implements Multicompany, Historical, OptimisticLocking{
private static final long serialVersionUID = 1L;

@EmbeddedId
private PartnerGroupMemberPk pk;

/**
* Partner group id
*/
@Column(name="PARTNER_GROUP_ID", nullable=false)
private String partnerGroupId;

/**
* Person id
*/
@Column(name="PERSON_ID", nullable=false)
private Long personId;

/**
* Responsability id
*/
@Column(name="RESPONSABILITY_ID", nullable=false)
private String responsabilityId;

/**
* Activity
*/
@Column(name="OBSERVATIONS", nullable=true)
private String observations;

public PartnerGroupMember() {
}
public PartnerGroupMember(PartnerGroupMemberPk pk) {
this.pk = pk;
}
public PartnerGroupMember(PartnerGroupMemberPk pk,String partnerGroupId,Long personId,String responsabilityId) {
this.pk = pk;
this.partnerGroupId = partnerGroupId;
this.personId = personId;
this.responsabilityId = responsabilityId;
}
public PartnerGroupMemberPk getPk() {
return this.pk;
}
public void setPk(PartnerGroupMemberPk pk) {
this.pk = pk;
}
public String getPartnerGroupId() {
return this.partnerGroupId;
}
public void setPartnerGroupId(String partnerGroupId) {
this.partnerGroupId = partnerGroupId;
}
public Long getPersonId() {
return this.personId;
}
public void setPersonId(Long personId) {
this.personId = personId;
}
public String getResponsabilityId() {
return this.responsabilityId;
}
public void setResponsabilityId(String responsabilityId) {
this.responsabilityId = responsabilityId;
}
public String getObservations() {
return this.observations;
}
public void setObservations(String observations) {
this.observations = observations;
}

@Override
public void setPk(Object pk) {
this.pk=(PartnerGroupMemberPk) pk;
}

@Override
public Object clone() throws CloneNotSupportedException {
PartnerGroupMember copy = (PartnerGroupMember) super.clone();
copy.setPk((PartnerGroupMemberPk) this.pk.clone());
return copy;
}

@Override
public String toString() {
return "PARTNER_GROUP_MEMBER:[" +
this.getPk().toString() + ", " +
this.getCreated() + ", " +
this.getPartnerGroupId() + ", " +
this.getPersonId() + ", " +
this.getResponsabilityId() + ", " +
this.getObservations() + ", " +
this.getVersion() + "]";
}
}
