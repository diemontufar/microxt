package mobile.entity.person;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the PERSON_TYPE database table.
 */
@Embeddable
public class PersonTypePk extends AbstractCompanyLanguageKey implements
		MulticompanyKey, MultilanguageKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Person type Id
	 */
	@Column(name = "PERSON_TYPE_ID", nullable = false)
	private String personTypeId;

	public PersonTypePk() {
	}

	public PersonTypePk(String personTypeId) {
		this.personTypeId = personTypeId;
	}

	public String getPersonTypeId() {
		return this.personTypeId;
	}

	public void setPersonTypeId(String personTypeId) {
		this.personTypeId = personTypeId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", "
				+ this.getPersonTypeId() + "]";
	}
}
