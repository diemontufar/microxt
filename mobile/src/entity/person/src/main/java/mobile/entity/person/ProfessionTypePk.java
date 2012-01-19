package mobile.entity.person;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the PROFESSION_TYPE database table.
 */
@Embeddable
public class ProfessionTypePk extends AbstractCompanyLanguageKey implements MulticompanyKey, MultilanguageKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Profession type Id
	 */
	@Column(name = "PROFESSION_TYPE_ID", nullable = false)
	private String professionTypeId;

	public ProfessionTypePk() {
	}

	public ProfessionTypePk(String professionTypeId) {
		this.professionTypeId = professionTypeId;
	}

	public String getProfessionTypeId() {
		return this.professionTypeId;
	}

	public void setProfessionTypeId(String professionTypeId) {
		this.professionTypeId = professionTypeId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getProfessionTypeId() + "]";
	}
}
