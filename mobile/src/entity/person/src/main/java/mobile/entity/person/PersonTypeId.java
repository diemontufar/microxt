package mobile.entity.person;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the PERSON_TYPE_ID database table.
 */
@Entity
@Table(name = "PERSON_TYPE_ID")
public class PersonTypeId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Person type Id
	 */
	@Id
	@Column(name = "PERSON_TYPE_ID", nullable = false)
	private String personTypeId;

	public PersonTypeId() {
	}

	public PersonTypeId(String personTypeId) {
		this.personTypeId = personTypeId;
	}

	public String getPersonTypeId() {
		return this.personTypeId;
	}

	public void setPersonTypeId(String personTypeId) {
		this.personTypeId = personTypeId;
	}

	@Override
	public Object getPk() {
		return this.personTypeId;
	}

	@Override
	public void setPk(Object pk) {
		this.personTypeId = (String) pk;
	}

	@Override
	public String toString() {
		return "PERSON_TYPE_ID:[" + this.getPk().toString() + "]";
	}
}
