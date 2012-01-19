package mobile.entity.marketing;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistorical;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;
import mobile.entity.schema.Historical;

/**
 * The persistent class for the PARTNER database table. Partners
 */
@Entity
@Table(name = "PARTNER")
public class Partner extends AbstractHistorical implements Multicompany, Multilanguage, Historical {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PartnerPk pk;

	/**
	 * Person id
	 */
	@Column(name = "PERSON_ID", nullable = false)
	private Long personId;

	/**
	 * Activity
	 */
	@Column(name = "ACTIVITY", nullable = true)
	private String activity;

	/**
	 * Assessor id
	 */
	@Column(name = "ASESSOR_ID", nullable = false)
	private String asessorId;

	/**
	 * Meeting Frequency id
	 */
	@Column(name = "MEETING_FREQUENCY_ID", nullable = true)
	private String meetingFrequencyId;

	/**
	 * Meeting day
	 */
	@Column(name = "MEETING_DAY", nullable = true)
	private Integer meetingDay;

	public Partner() {
	}

	public Partner(PartnerPk pk) {
		this.pk = pk;
	}

	public Partner(PartnerPk pk, Long personId, String asessorId) {
		this.pk = pk;
		this.personId = personId;
		this.asessorId = asessorId;
	}

	public PartnerPk getPk() {
		return this.pk;
	}

	public void setPk(PartnerPk pk) {
		this.pk = pk;
	}

	public Long getPersonId() {
		return this.personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
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
		this.pk = (PartnerPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Partner copy = (Partner) super.clone();
		copy.setPk((PartnerPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "PARTNER:[" + this.getPk().toString() + ", " + this.getCreated() + ", " + this.getPersonId() + ", "
				+ this.getActivity() + ", " + this.getAsessorId() + ", " + this.getMeetingFrequencyId() + ", "
				+ this.getMeetingDay() + "]";
	}
}
