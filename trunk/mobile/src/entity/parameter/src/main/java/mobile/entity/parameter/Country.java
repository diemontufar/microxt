package mobile.entity.parameter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the COUNTRY database table. Values of countries
 */
@Entity
@Table(name = "COUNTRY")
public class Country extends AbstractEntity implements Multicompany,
		Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CountryPk pk;

	/**
	 * Name of country
	 */
	@Column(name = "NAME", nullable = false)
	private String name;

	/**
	 * Area code
	 */
	@Column(name = "AREA_CODE", nullable = true)
	private String areaCode;

	public Country() {
	}

	public Country(CountryPk pk) {
		this.pk = pk;
	}

	public Country(CountryPk pk, String name) {
		this.pk = pk;
		this.name = name;
	}

	public CountryPk getPk() {
		return this.pk;
	}

	public void setPk(CountryPk pk) {
		this.pk = pk;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (CountryPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Country copy = (Country) super.clone();

		copy.setPk((CountryPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "COUNTRY:[" + this.getPk().toString() + ", " + this.getName()
				+ ", " + this.getAreaCode() + "]";
	}
}
