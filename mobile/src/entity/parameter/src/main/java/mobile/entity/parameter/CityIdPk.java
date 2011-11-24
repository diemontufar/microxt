package mobile.entity.parameter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import mobile.entity.schema.AbstractEntityKey;
import mobile.entity.schema.GeneralEntityKey;

/**
 * The primary key class for the CITY_ID database table.
 */
@Embeddable
public class CityIdPk extends AbstractEntityKey implements GeneralEntityKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Country Id
	 */
	@Column(name = "COUNTRY_ID", nullable = false)
	private String countryId;

	/**
	 * Province Id
	 */
	@Column(name = "PROVINCE_ID", nullable = false)
	private String provinceId;

	/**
	 * City Id
	 */
	@Column(name = "CITY_ID", nullable = false)
	private String cityId;

	public CityIdPk() {
	}

	public CityIdPk(String countryId, String provinceId, String cityId) {
		this.countryId = countryId;
		this.provinceId = provinceId;
		this.cityId = cityId;
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

	@Override
	public String toString() {
		return "[" + this.getCountryId() + ", " + this.getProvinceId() + ", "
				+ this.getCityId() + "]";
	}
}
