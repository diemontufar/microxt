package mobile.entity.marketing;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;
import mobile.entity.schema.HistoricalKey;

/**
 * The primary key class for the MEETING_FREQUENCY database table.
 */
@Embeddable
public class MeetingFrequencyPk extends AbstractCompanyLanguageHistoricalKey implements MulticompanyKey,
		MultilanguageKey, HistoricalKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Meeting Frequency id
	 */
	@Column(name = "MEETING_FREQUENCY_ID", nullable = false)
	private String meetingFrequencyId;

	public MeetingFrequencyPk() {
	}

	public MeetingFrequencyPk(String meetingFrequencyId) {
		this.meetingFrequencyId = meetingFrequencyId;
	}

	public String getMeetingFrequencyId() {
		return this.meetingFrequencyId;
	}

	public void setMeetingFrequencyId(String meetingFrequencyId) {
		this.meetingFrequencyId = meetingFrequencyId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getExpired() + ", "
				+ this.getMeetingFrequencyId() + "]";
	}
}
