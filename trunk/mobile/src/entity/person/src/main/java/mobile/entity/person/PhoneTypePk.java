package mobile.entity.person;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the PHONE_TYPE database table.
 */
@Embeddable
public class PhoneTypePk extends AbstractCompanyLanguageKey implements MulticompanyKey, MultilanguageKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Phone type Id
	 */
	@Column(name = "PHONE_TYPE_ID", nullable = false)
	private String phoneTypeId;

	public PhoneTypePk() {
	}

	public PhoneTypePk(String phoneTypeId) {
		this.phoneTypeId = phoneTypeId;
	}

	public String getPhoneTypeId() {
		return this.phoneTypeId;
	}

	public void setPhoneTypeId(String phoneTypeId) {
		this.phoneTypeId = phoneTypeId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getPhoneTypeId() + "]";
	}
}
