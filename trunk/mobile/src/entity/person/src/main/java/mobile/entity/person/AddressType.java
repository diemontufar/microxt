package mobile.entity.person;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the ADDRESS_TYPE database table. Values of address
 * types
 */
@Entity
@Table(name = "ADDRESS_TYPE")
public class AddressType extends AbstractEntity implements Multicompany,
		Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AddressTypePk pk;

	/**
	 * Name of address type
	 */
	@Column(name = "NAME", nullable = false)
	private String name;

	public AddressType() {
	}

	public AddressType(AddressTypePk pk) {
		this.pk = pk;
	}

	public AddressType(AddressTypePk pk, String name) {
		this.pk = pk;
		this.name = name;
	}

	public AddressTypePk getPk() {
		return this.pk;
	}

	public void setPk(AddressTypePk pk) {
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
		this.pk = (AddressTypePk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		AddressType copy = (AddressType) super.clone();
		copy.setPk((AddressTypePk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "ADDRESS_TYPE:[" + this.getPk().toString() + ", "
				+ this.getName() + "]";
	}
}
