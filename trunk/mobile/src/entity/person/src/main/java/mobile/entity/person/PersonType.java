package mobile.entity.person;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the PERSON_TYPE database table. Values of person
 * types
 */
@Entity
@Table(name = "PERSON_TYPE")
public class PersonType extends AbstractEntity implements Multicompany,
		Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonTypePk pk;

	/**
	 * Name of person type
	 */
	@Column(name = "NAME", nullable = false)
	private String name;

	public PersonType() {
	}

	public PersonType(PersonTypePk pk) {
		this.pk = pk;
	}

	public PersonType(PersonTypePk pk, String name) {
		this.pk = pk;
		this.name = name;
	}

	public PersonTypePk getPk() {
		return this.pk;
	}

	public void setPk(PersonTypePk pk) {
		this.pk = pk;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (PersonTypePk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		PersonType copy = (PersonType) super.clone();

		copy.setPk((PersonTypePk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "PERSON_TYPE:[" + this.getPk().toString() + ", "
				+ this.getName() + "]";
	}
}
