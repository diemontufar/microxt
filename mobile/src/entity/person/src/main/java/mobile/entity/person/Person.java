package mobile.entity.person;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mobile.entity.schema.AbstractHistoricalLocking;
import mobile.entity.schema.Historical;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.OptimisticLocking;

/**
 * The persistent class for the PERSON database table. Values of person
 */
@Entity
@Table(name = "PERSON")
public class Person extends AbstractHistoricalLocking implements Multicompany,
		Historical, OptimisticLocking {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonPk pk;

	/**
	 * Name of person
	 */
	@Column(name = "NAME", nullable = false)
	private String name;

	/**
	 * Lastname of person
	 */
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	/**
	 * Second lastname of person
	 */
	@Column(name = "SECOND_LAST_NAME", nullable = true)
	private String secondLastName;

	/**
	 * Identification type Id
	 */
	@Column(name = "IDENTIFICATION_TYPE_ID", nullable = false)
	private String identificationTypeId;

	/**
	 * Identification number
	 */
	@Column(name = "IDENTIFICATION_NUMBER", nullable = false)
	private String identificationNumber;

	/**
	 * Date of birth
	 */
	@Column(name = "DATE_OF_BIRTH", nullable = false)
	private Date dateOfBirth;

	/**
	 * Gender type Id
	 */
	@Column(name = "GENDER_TYPE_ID", nullable = false)
	private String genderTypeId;

	/**
	 * Civil type status Id
	 */
	@Column(name = "CIVIL_STATUS_ID", nullable = false)
	private String civilStatusId;

	/**
	 * Country Id
	 */
	@Column(name = "COUNTRY_ID", nullable = false)
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
	 * Profession type Id
	 */
	@Column(name = "PROFESSION_TYPE_ID", nullable = true)
	private String professionTypeId;

	public Person() {
	}

	public Person(PersonPk pk) {
		this.pk = pk;
	}

	public Person(PersonPk pk, String name, String lastName,
			String identificationTypeId, String identificationNumber,
			Date dateOfBirth, String genderTypeId, String civilStatusId,
			String countryId) {
		this.pk = pk;
		this.name = name;
		this.lastName = lastName;
		this.identificationTypeId = identificationTypeId;
		this.identificationNumber = identificationNumber;
		this.dateOfBirth = dateOfBirth;
		this.genderTypeId = genderTypeId;
		this.civilStatusId = civilStatusId;
		this.countryId = countryId;
	}

	public PersonPk getPk() {
		return this.pk;
	}

	public void setPk(PersonPk pk) {
		this.pk = pk;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSecondLastName() {
		return this.secondLastName;
	}

	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}

	public String getIdentificationTypeId() {
		return this.identificationTypeId;
	}

	public void setIdentificationTypeId(String identificationTypeId) {
		this.identificationTypeId = identificationTypeId;
	}

	public String getIdentificationNumber() {
		return this.identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGenderTypeId() {
		return this.genderTypeId;
	}

	public void setGenderTypeId(String genderTypeId) {
		this.genderTypeId = genderTypeId;
	}

	public String getCivilStatusId() {
		return this.civilStatusId;
	}

	public void setCivilStatusId(String civilStatusId) {
		this.civilStatusId = civilStatusId;
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

	public String getProfessionTypeId() {
		return this.professionTypeId;
	}

	public void setProfessionTypeId(String professionTypeId) {
		this.professionTypeId = professionTypeId;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (PersonPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Person copy = (Person) super.clone();

		copy.setPk((PersonPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "PERSON:[" + this.getPk().toString() + ", " + this.getCreated()
				+ ", " + this.getName() + ", " + this.getLastName() + ", "
				+ this.getSecondLastName() + ", "
				+ this.getIdentificationTypeId() + ", "
				+ this.getIdentificationNumber() + ", " + this.getDateOfBirth()
				+ ", " + this.getGenderTypeId() + ", "
				+ this.getCivilStatusId() + ", " + this.getCountryId() + ", "
				+ this.getProvinceId() + ", " + this.getCityId() + ", "
				+ this.getProfessionTypeId() + ", " + this.getVersion() + "]";
	}
}
