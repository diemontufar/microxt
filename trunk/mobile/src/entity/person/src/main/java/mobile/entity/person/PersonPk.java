package mobile.entity.person;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.HistoricalKey;
import mobile.entity.schema.SequentialKey;

/**
 * The primary key class for the PERSON database table.
 */
@Embeddable
public class PersonPk extends AbstractCompanyHistoricalKey implements MulticompanyKey, HistoricalKey, SequentialKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Person Id
	 */
	@Column(name = "PERSON_ID", nullable = false)
	private Integer personId;

	public PersonPk() {
	}

	public PersonPk(Integer personId) {
		this.personId = personId;
	}

	public Integer getPersonId() {
		return this.personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getExpired() + ", " + this.getPersonId() + "]";
	}

	@Override
	public Integer getId() {
		return personId;
	}

	@Override
	public void setId(Integer arg0) {
		this.personId=arg0;
		
	}
}
