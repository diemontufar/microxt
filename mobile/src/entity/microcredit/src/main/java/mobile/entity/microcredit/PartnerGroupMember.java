package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.GeneralEntity;

/**
 * The persistent class for the PARTNER_GROUP_MEMBER database table. Group
 * Members
 */
@Entity
@Table(name = "PARTNER_GROUP_MEMBER")
public class PartnerGroupMember extends AbstractEntity implements GeneralEntity {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PartnerGroupMemberPk pk;

	/**
	 * Responsability id
	 */
	@Column(name = "RESPONSABILITY_ID", nullable = false)
	private String responsabilityId;

	/**
	 * Observations
	 */
	@Column(name = "OBSERVATIONS", nullable = true)
	private String observations;

	public PartnerGroupMember() {
	}

	public PartnerGroupMember(PartnerGroupMemberPk pk) {
		this.pk = pk;
	}

	public PartnerGroupMember(PartnerGroupMemberPk pk, String responsabilityId) {
		this.pk = pk;
		this.responsabilityId = responsabilityId;
	}

	public PartnerGroupMemberPk getPk() {
		return this.pk;
	}

	public void setPk(PartnerGroupMemberPk pk) {
		this.pk = pk;
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
		this.pk = (PartnerGroupMemberPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		PartnerGroupMember copy = (PartnerGroupMember) super.clone();
		copy.setPk((PartnerGroupMemberPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "PARTNER_GROUP_MEMBER:[" + this.getPk().toString() + ", " + this.getResponsabilityId() + ", "
				+ this.getObservations() + "]";
	}
}
