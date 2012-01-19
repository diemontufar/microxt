package mobile.entity.person;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.HistoricalKey;

/**
 * The primary key class for the PERSON_PHONE database table.
 */
@Embeddable
public class PersonPhonePk extends AbstractCompanyHistoricalKey implements MulticompanyKey, HistoricalKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Person Id
	 */
	@Column(name = "PERSON_ID", nullable = false)
	private Long personId;

	/**
	 * Sequence of person phone
	 */
	@Column(name = "PHONE_SEQUENCE", nullable = false)
	private Integer phoneSequence;

	public PersonPhonePk() {
	}

	public PersonPhonePk(Long personId, Integer phoneSequence) {
		this.personId = personId;
		this.phoneSequence = phoneSequence;
	}

	public Long getPersonId() {
		return this.personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Integer getPhoneSequence() {
		return this.phoneSequence;
	}

	public void setPhoneSequence(Integer phoneSequence) {
		this.phoneSequence = phoneSequence;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getExpired() + ", " + this.getPersonId() + ", "
				+ this.getPhoneSequence() + "]";
	}
}
