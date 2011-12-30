package mobile.entity.parameter;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the DISTRICT database table.
 */
@Embeddable
public class DistrictPk extends AbstractCompanyLanguageKey implements
		MulticompanyKey, MultilanguageKey {
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

	/**
	 * District Id
	 */
	@Column(name = "DISTRICT_ID", nullable = false)
	private String districtId;

	public DistrictPk() {
	}

	public DistrictPk(String countryId, String provinceId, String cityId,
			String districtId) {
		this.countryId = countryId;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.districtId = districtId;
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

	public String getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", "
				+ this.getCountryId() + ", " + this.getProvinceId() + ", "
				+ this.getCityId() + ", " + this.getDistrictId() + "]";
	}
}
