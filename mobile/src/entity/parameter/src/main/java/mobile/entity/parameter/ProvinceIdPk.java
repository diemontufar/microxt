package mobile.entity.parameter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import mobile.entity.schema.AbstractEntityKey;
import mobile.entity.schema.GeneralEntityKey;

/**
 * The primary key class for the PROVINCE_ID database table.
 */
@Embeddable
public class ProvinceIdPk extends AbstractEntityKey implements GeneralEntityKey {
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

	public ProvinceIdPk() {
	}

	public ProvinceIdPk(String countryId, String provinceId) {
		this.countryId = countryId;
		this.provinceId = provinceId;
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

	@Override
	public String toString() {
		return "[" + this.getCountryId() + ", " + this.getProvinceId() + "]";
	}
}
