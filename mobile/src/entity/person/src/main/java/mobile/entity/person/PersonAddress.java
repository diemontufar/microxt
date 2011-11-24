package mobile.entity.person;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mobile.entity.schema.AbstractHistoricalLocking;
import mobile.entity.schema.Historical;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.OptimisticLocking;

/**
 * The persistent class for the PERSON_ADDRESS database table. Values of person
 * address
 */
@Entity
@Table(name = "PERSON_ADDRESS")
public class PersonAddress extends AbstractHistoricalLocking implements
		Multicompany, Historical, OptimisticLocking {
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
	@Column(name = "FIRST_STREET", nullable = false)
	private String firstStreet;

	/**
	 * Address number
	 */
	@Column(name = "ADDRESS_NUMBER", nullable = false)
	private String addressNumber;

	/**
	 * Second street
	 */
	@Column(name = "SECOND_STREET", nullable = true)
	private String secondStreet;

	/**
	 * Building name
	 */
	@Column(name = "BUILDING_NAME", nullable = true)
	private String buildingName;

	/**
	 * Building number
	 */
	@Column(name = "BUILDING_FLOOR", nullable = true)
	private String buildingFloor;

	/**
	 * Country Id
	 */
	@Column(name = "COUNTRY_ID", nullable = true)
	private String countryId;

	/**
	 * Province Id
	 */
	@Column(name = "PROVINCE_ID", nullable = true)
	private String provinceId;

	/**
	 * City Id
	 */
	@Column(name = "CITY_ID", nullable = true)
	private String cityId;

	/**
	 * Address map
	 */
	@Column(name = "ADDRESS_MAP", nullable = true)
	private String addressMap;

	public PersonAddress() {
	}

	public PersonAddress(PersonAddressPk pk) {
		this.pk = pk;
	}

	public PersonAddress(PersonAddressPk pk, String addressTypeId,
			String firstStreet, String addressNumber) {
		this.pk = pk;
		this.addressTypeId = addressTypeId;
		this.firstStreet = firstStreet;
		this.addressNumber = addressNumber;
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

	public String getFirstStreet() {
		return this.firstStreet;
	}

	public void setFirstStreet(String firstStreet) {
		this.firstStreet = firstStreet;
	}

	public String getAddressNumber() {
		return this.addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getSecondStreet() {
		return this.secondStreet;
	}

	public void setSecondStreet(String secondStreet) {
		this.secondStreet = secondStreet;
	}

	public String getBuildingName() {
		return this.buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getBuildingFloor() {
		return this.buildingFloor;
	}

	public void setBuildingFloor(String buildingFloor) {
		this.buildingFloor = buildingFloor;
	}

	public String getCountryId() {
		return this.countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return this.cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getAddressMap() {
		return this.addressMap;
	}

	public void setAddressMap(String addressMap) {
		this.addressMap = addressMap;
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
		return "PERSON_ADDRESS:[" + this.getPk().toString() + ", "
				+ this.getCreated() + ", " + this.getAddressTypeId() + ", "
				+ this.getFirstStreet() + ", " + this.getAddressNumber() + ", "
				+ this.getSecondStreet() + ", " + this.getBuildingName() + ", "
				+ this.getBuildingFloor() + ", " + this.getCountryId() + ", "
				+ this.getProvinceId() + ", " + this.getCityId() + ", "
				+ this.getAddressMap() + ", " + this.getVersion() + "]";
	}
}
