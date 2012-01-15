package mobile.entity.marketing;

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
	@Column(name = "ASESSOR_ID", nullable = false)
	private String asessorId;

	/**
	 * Meeting Frequency id
	 */
	@Column(name = "MEETING_FREQUENCY_ID", nullable = false)
	private String meetingFrequencyId;

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

	public PartnerGroup(PartnerGroupPk pk, Date creationDate, String asessorId,
			String meetingFrequencyId) {
		this.pk = pk;
		this.creationDate = creationDate;
		this.asessorId = asessorId;
		this.meetingFrequencyId = meetingFrequencyId;
	}

	public PartnerGroupPk getPk() {
		return this.pk;
	}

	public void setPk(PartnerGroupPk pk) {
		this.pk = pk;
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

	public String getAsessorId() {
		return this.asessorId;
	}

	public void setAsessorId(String asessorId) {
		this.asessorId = asessorId;
	}

	public String getMeetingFrequencyId() {
		return this.meetingFrequencyId;
	}

	public void setMeetingFrequencyId(String meetingFrequencyId) {
		this.meetingFrequencyId = meetingFrequencyId;
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
				+ this.getCreated() + ", " + this.getCreationDate() + ", "
				+ this.getActivity() + ", " + this.getAsessorId() + ", "
				+ this.getMeetingFrequencyId() + ", " + this.getMeetingDay()
				+ "]";
	}
}
