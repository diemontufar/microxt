package mobile.entity.microcredit;

import java.sql.Date;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistorical;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;
import mobile.entity.schema.Historical;

/**
 * The persistent class for the PARTNER_GROUP database table. Partner groups
 */
@Entity
@Table(name = "PARTNER_GROUP")
public class PartnerGroup extends AbstractHistorical implements Multicompany,
		Multilanguage, Historical {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PartnerGroupPk pk;

	/**
	 * Group description
	 */
	@Column(name = "GROUP_DESCRIPTION", nullable = false)
	private String groupDescription;

	/**
	 * Creation date
	 */
	@Column(name = "CREATION_DATE", nullable = false)
	private Date creationDate;

	/**
	 * Activity
	 */
	@Column(name = "ACTIVITY", nullable = true)
	private String activity;

	/**
	 * Assessor
	 */
	@Column(name = "USER_ID", nullable = false)
	private String userId;

	/**
	 * Meeting Frequency id
	 */
	@Column(name = "FREQUENCY_ID", nullable = false)
	private String frequencyId;

	/**
	 * Meeting day
	 */
	@Column(name = "MEETING_DAY", nullable = true)
	private Integer meetingDay;

	public PartnerGroup() {
	}

	public PartnerGroup(PartnerGroupPk pk) {
		this.pk = pk;
	}

	public PartnerGroup(PartnerGroupPk pk, String groupDescription,
			Date creationDate, String userId, String frequencyId) {
		this.pk = pk;
		this.groupDescription = groupDescription;
		this.creationDate = creationDate;
		this.userId = userId;
		this.frequencyId = frequencyId;
	}

	public PartnerGroupPk getPk() {
		return this.pk;
	}

	public void setPk(PartnerGroupPk pk) {
		this.pk = pk;
	}

	public String getGroupDescription() {
		return this.groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getActivity() {
		return this.activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFrequencyId() {
		return this.frequencyId;
	}

	public void setFrequencyId(String frequencyId) {
		this.frequencyId = frequencyId;
	}

	public Integer getMeetingDay() {
		return this.meetingDay;
	}

	public void setMeetingDay(Integer meetingDay) {
		this.meetingDay = meetingDay;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (PartnerGroupPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		PartnerGroup copy = (PartnerGroup) super.clone();
		copy.setPk((PartnerGroupPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "PARTNER_GROUP:[" + this.getPk().toString() + ", "
				+ this.getCreated() + ", " + this.getGroupDescription() + ", "
				+ this.getCreationDate() + ", " + this.getActivity() + ", "
				+ this.getUserId() + ", " + this.getFrequencyId() + ", "
				+ this.getMeetingDay() + "]";
	}
}
