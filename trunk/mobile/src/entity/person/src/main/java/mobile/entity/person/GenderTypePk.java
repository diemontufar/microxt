package mobile.entity.person;

import javax.persistence.*;

import mobile.entity.schema.AbstractLanguageKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the GENDER_TYPE database table.
 */
@Embeddable
public class GenderTypePk extends AbstractLanguageKey implements
		MultilanguageKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Gender type Id
	 */
	@Column(name = "GENDER_TYPE_ID", nullable = false)
	private String genderTypeId;

	public GenderTypePk() {
	}

	public GenderTypePk(String genderTypeId) {
		this.genderTypeId = genderTypeId;
	}

	public String getGenderTypeId() {
		return this.genderTypeId;
	}

	public void setGenderTypeId(String genderTypeId) {
		this.genderTypeId = genderTypeId;
	}

	@Override
	public String toString() {
		return "[" + this.getLanguageId() + ", " + this.getGenderTypeId() + "]";
	}
}
