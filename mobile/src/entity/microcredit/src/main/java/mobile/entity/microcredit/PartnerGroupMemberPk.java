package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityKey;
import mobile.entity.schema.GeneralEntityKey;

/**
 * The primary key class for the PARTNER_GROUP_MEMBER database table.
 */
@Embeddable
public class PartnerGroupMemberPk extends AbstractEntityKey implements GeneralEntityKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Partner group id
	 */
	@Column(name = "PARTNER_GROUP_ID", nullable = false)
	private Integer partnerGroupId;

	/**
	 * Person id
	 */
	@Column(name = "PERSON_ID", nullable = false)
	private Integer personId;

	public PartnerGroupMemberPk() {
	}

	public PartnerGroupMemberPk(Integer partnerGroupId, Integer personId) {
		this.partnerGroupId = partnerGroupId;
		this.personId = personId;
	}

	public Integer getPartnerGroupId() {
		return this.partnerGroupId;
	}

	public void setPartnerGroupId(Integer partnerGroupId) {
		this.partnerGroupId = partnerGroupId;
	}

	public Integer getPersonId() {
		return this.personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	@Override
	public String toString() {
		return "[" + this.getPartnerGroupId() + ", " + this.getPersonId() + "]";
	}
}
