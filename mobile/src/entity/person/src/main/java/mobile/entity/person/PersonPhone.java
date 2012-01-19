package mobile.entity.person;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistoricalLocking;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Historical;
import mobile.entity.schema.OptimisticLocking;

/**
 * The persistent class for the PERSON_PHONE database table. Values of person
 * phones
 */
@Entity
@Table(name = "PERSON_PHONE")
public class PersonPhone extends AbstractHistoricalLocking implements Multicompany, Historical, OptimisticLocking {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonPhonePk pk;

	/**
	 * Phone type Id
	 */
	@Column(name = "PHONE_TYPE_ID", nullable = false)
	private String phoneTypeId;

	/**
	 * Area code
	 */
	@Column(name = "AREA_CODE", nullable = false)
	private String areaCode;

	/**
	 * Phone number
	 */
	@Column(name = "PHONE_NUMBER", nullable = false)
	private String phoneNumber;

	public PersonPhone() {
	}

	public PersonPhone(PersonPhonePk pk) {
		this.pk = pk;
	}

	public PersonPhone(PersonPhonePk pk, String phoneTypeId, String areaCode, String phoneNumber) {
		this.pk = pk;
		this.phoneTypeId = phoneTypeId;
		this.areaCode = areaCode;
		this.phoneNumber = phoneNumber;
	}

	public PersonPhonePk getPk() {
		return this.pk;
	}

	public void setPk(PersonPhonePk pk) {
		this.pk = pk;
	}

	public String getPhoneTypeId() {
		return this.phoneTypeId;
	}

	public void setPhoneTypeId(String phoneTypeId) {
		this.phoneTypeId = phoneTypeId;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (PersonPhonePk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		PersonPhone copy = (PersonPhone) super.clone();
		copy.setPk((PersonPhonePk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "PERSON_PHONE:[" + this.getPk().toString() + ", " + this.getCreated() + ", " + this.getPhoneTypeId()
				+ ", " + this.getAreaCode() + ", " + this.getPhoneNumber() + ", " + this.getVersion() + "]";
	}
}
