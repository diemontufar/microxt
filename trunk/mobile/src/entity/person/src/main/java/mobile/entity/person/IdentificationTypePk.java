package mobile.entity.person;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the IDENTIFICATION_TYPE database table.
 */
@Embeddable
public class IdentificationTypePk extends AbstractCompanyLanguageKey implements
		MulticompanyKey, MultilanguageKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Identification type Id
	 */
	@Column(name = "IDENTIFICATION_TYPE_ID", nullable = false)
	private String identificationTypeId;

	public IdentificationTypePk() {
	}

	public IdentificationTypePk(String identificationTypeId) {
		this.identificationTypeId = identificationTypeId;
	}

	public String getIdentificationTypeId() {
		return this.identificationTypeId;
	}

	public void setIdentificationTypeId(String identificationTypeId) {
		this.identificationTypeId = identificationTypeId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", "
				+ this.getIdentificationTypeId() + "]";
	}
}
