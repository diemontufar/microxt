package mobile.entity.person;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistoricalLocking;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Historical;
import mobile.entity.schema.OptimisticLocking;

/**
 * The persistent class for the PERSON_ADDRESS database table. Values of person
 * address
 */
@Entity
@Table(name = "PERSON_ADDRESS")
public class PersonAddress extends AbstractHistoricalLocking implements Multicompany, Historical, OptimisticLocking {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonAddressPk pk;

	/**
	 * Address type Id
	 */
	@Column(name = "ADDRESS_TYPE_ID", nullable = false)
	private String addressTypeId;

	/**
	 * First street
	 */
	@Column(name = "ADDRESS_DESCRIPTION", nullable = false)
	private String addressDescription;

	/**
	 * Country Id
	 */
	@Column(name = "COUNTRY_ID", nullable = true)
	private String countryId;

	/**
	 * City Id
	 */
	@Column(name = "CITY_ID", nullable = true)
	private String cityId;

	/**
	 * Province Id
	 */
	@Column(name = "PROVINCE_ID", nullable = true)
	private String provinceId;

	/**
	 * District Id
	 */
	@Column(name = "DISTRICT_ID", nullable = true)
	private String districtId;

	public PersonAddress() {
	}

	public PersonAddress(PersonAddressPk pk) {
		this.pk = pk;
	}

	public PersonAddress(PersonAddressPk pk, String addressTypeId, String addressDescription) {
		this.pk = pk;
		this.addressTypeId = addressTypeId;
		this.addressDescription = addressDescription;
	}

	public PersonAddressPk getPk() {
		return this.pk;
	}

	public void setPk(PersonAddressPk pk) {
		this.pk = pk;
	}

	public String getAddressTypeId() {
		return this.addressTypeId;
	}

	public void setAddressTypeId(String addressTypeId) {
		this.addressTypeId = addressTypeId;
	}

	public String getAddressDescription() {
		return this.addressDescription;
	}

	public void setAddressDescription(String addressDescription) {
		this.addressDescription = addressDescription;
	}

	public String getCountryId() {
		return this.countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCityId() {
		return this.cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (PersonAddressPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		PersonAddress copy = (PersonAddress) super.clone();
		copy.setPk((PersonAddressPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "PERSON_ADDRESS:[" + this.getPk().toString() + ", " + this.getCreated() + ", " + this.getAddressTypeId()
				+ ", " + this.getAddressDescription() + ", " + this.getCountryId() + ", " + this.getCityId() + ", "
				+ this.getProvinceId() + ", " + this.getDistrictId() + ", " + this.getVersion() + "]";
	}
}
