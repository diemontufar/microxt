package mobile.entity.person;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the ADDRESS_TYPE database table.
 */
@Embeddable
public class AddressTypePk extends AbstractCompanyLanguageKey implements
		MulticompanyKey, MultilanguageKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Address type Id
	 */
	@Column(name = "ADDRESS_TYPE_ID", nullable = false)
	private String addressTypeId;

	public AddressTypePk() {
	}

	public AddressTypePk(String addressTypeId) {
		this.addressTypeId = addressTypeId;
	}

	public String getAddressTypeId() {
		return this.addressTypeId;
	}

	public void setAddressTypeId(String addressTypeId) {
		this.addressTypeId = addressTypeId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", "
				+ this.getAddressTypeId() + "]";
	}
}
