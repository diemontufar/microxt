package mobile.entity.microcredit;

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
	private Integer personId;

	/**
	 * Activity
	 */
	@Column(name = "ACTIVITY", nullable = true)
	private String activity;

	/**
	 * User Id
	 */
	@Column(name = "USER_ID", nullable = false)
	private String userId;

	/**
	 * Meeting Frequency id
	 */
	@Column(name = "FREQUENCY_ID", nullable = true)
	private String frequencyId;

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

	public Partner(PartnerPk pk, Integer personId, String userId) {
		this.pk = pk;
		this.personId = personId;
		this.userId = userId;
	}

	public PartnerPk getPk() {
		return this.pk;
	}

	public void setPk(PartnerPk pk) {
		this.pk = pk;
	}

	public Integer  getPersonId() {
		return this.personId;
	}

	public void setPersonId(Integer  personId) {
		this.personId = personId;
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
				+ this.getActivity() + ", " + this.getUserId() + ", " + this.getFrequencyId() + ", "
				+ this.getMeetingDay() + "]";
	}
}
