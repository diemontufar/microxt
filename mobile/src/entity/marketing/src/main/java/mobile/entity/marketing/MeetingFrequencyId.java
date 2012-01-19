package mobile.entity.marketing;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the MEETING_FREQUENCY_ID database table.
 */
@Entity
@Table(name = "MEETING_FREQUENCY_ID")
public class MeetingFrequencyId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Meeting Frequency id
	 */
	@Id
	@Column(name = "MEETING_FREQUENCY_ID", nullable = false)
	private String meetingFrequencyId;

	public MeetingFrequencyId() {
	}

	public MeetingFrequencyId(String meetingFrequencyId) {
		this.meetingFrequencyId = meetingFrequencyId;
	}

	public String getMeetingFrequencyId() {
		return this.meetingFrequencyId;
	}

	public void setMeetingFrequencyId(String meetingFrequencyId) {
		this.meetingFrequencyId = meetingFrequencyId;
	}

	@Override
	public Object getPk() {
		return this.meetingFrequencyId;
	}

	@Override
	public void setPk(Object pk) {
		this.meetingFrequencyId = (String) pk;
	}

	@Override
	public String toString() {
		return "MEETING_FREQUENCY_ID:[" + this.getPk().toString() + "]";
	}
}
