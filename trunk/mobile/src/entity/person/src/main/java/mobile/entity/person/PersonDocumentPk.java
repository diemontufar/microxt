package mobile.entity.person;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.HistoricalKey;

/**
 * The primary key class for the PERSON_DOCUMENT database table.
 */
@Embeddable
public class PersonDocumentPk extends AbstractCompanyHistoricalKey implements
		MulticompanyKey, HistoricalKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Person Id
	 */
	@Column(name = "PERSON_ID", nullable = false)
	private Long personId;

	/**
	 * Sequence of person document
	 */
	@Column(name = "DOCUMENT_SEQUENCE", nullable = false)
	private Integer documentSequence;

	public PersonDocumentPk() {
	}

	public PersonDocumentPk(Long personId, Integer documentSequence) {
		this.personId = personId;
		this.documentSequence = documentSequence;
	}

	public Long getPersonId() {
		return this.personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public Integer getDocumentSequence() {
		return this.documentSequence;
	}

	public void setDocumentSequence(Integer documentSequence) {
		this.documentSequence = documentSequence;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getExpired() + ", "
				+ this.getPersonId() + ", " + this.getDocumentSequence() + "]";
	}
}
