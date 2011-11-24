package mobile.entity.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the GENDER_TYPE_ID database table.
 */
@Entity
@Table(name = "GENDER_TYPE_ID")
public class GenderTypeId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Gender type Id
	 */
	@Id
	@Column(name = "GENDER_TYPE_ID", nullable = false)
	private String genderTypeId;

	public GenderTypeId() {
	}

	public GenderTypeId(String genderTypeId) {
		this.genderTypeId = genderTypeId;
	}

	public String getGenderTypeId() {
		return this.genderTypeId;
	}

	public void setGenderTypeId(String genderTypeId) {
		this.genderTypeId = genderTypeId;
	}

	@Override
	public Object getPk() {
		return this.genderTypeId;
	}

	@Override
	public void setPk(Object pk) {
		this.genderTypeId = (String) pk;
	}

	@Override
	public String toString() {
		return "GENDER_TYPE_ID:[" + this.getPk().toString() + "]";
	}
}
