package mobile.entity.marketing;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistorical;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;
import mobile.entity.schema.Historical;

/**
 * The persistent class for the MEETING_FREQUENCY database table. Meeting
 * frequency
 */
@Entity
@Table(name = "MEETING_FREQUENCY")
public class MeetingFrequency extends AbstractHistorical implements Multicompany, Multilanguage, Historical {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MeetingFrequencyPk pk;

	/**
	 * Frequency
	 */
	@Column(name = "FREQUENCY", nullable = false)
	private String frequency;

	/**
	 * Observations
	 */
	@Column(name = "OBSERVATIONS", nullable = true)
	private String observations;

	public MeetingFrequency() {
	}

	public MeetingFrequency(MeetingFrequencyPk pk) {
		this.pk = pk;
	}

	public MeetingFrequency(MeetingFrequencyPk pk, String frequency) {
		this.pk = pk;
		this.frequency = frequency;
	}

	public MeetingFrequencyPk getPk() {
		return this.pk;
	}

	public void setPk(MeetingFrequencyPk pk) {
		this.pk = pk;
	}

	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getObservations() {
		return this.observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (MeetingFrequencyPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		MeetingFrequency copy = (MeetingFrequency) super.clone();
		copy.setPk((MeetingFrequencyPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "MEETING_FREQUENCY:[" + this.getPk().toString() + ", " + this.getCreated() + ", " + this.getFrequency()
				+ ", " + this.getObservations() + "]";
	}
}
