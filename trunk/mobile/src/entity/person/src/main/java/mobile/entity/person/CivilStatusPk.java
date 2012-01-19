package mobile.entity.person;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the CIVIL_STATUS database table.
 */
@Embeddable
public class CivilStatusPk extends AbstractCompanyLanguageKey implements MulticompanyKey, MultilanguageKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Civil type status Id
	 */
	@Column(name = "CIVIL_STATUS_ID", nullable = false)
	private String civilStatusId;

	public CivilStatusPk() {
	}

	public CivilStatusPk(String civilStatusId) {
		this.civilStatusId = civilStatusId;
	}

	public String getCivilStatusId() {
		return this.civilStatusId;
	}

	public void setCivilStatusId(String civilStatusId) {
		this.civilStatusId = civilStatusId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getCivilStatusId() + "]";
	}
}
