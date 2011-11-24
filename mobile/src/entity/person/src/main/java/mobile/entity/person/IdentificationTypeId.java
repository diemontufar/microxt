package mobile.entity.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the IDENTIFICATION_TYPE_ID database table.
 */
@Entity
@Table(name = "IDENTIFICATION_TYPE_ID")
public class IdentificationTypeId extends AbstractEntityId implements
		GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Identification type Id
	 */
	@Id
	@Column(name = "IDENTIFICATION_TYPE_ID", nullable = false)
	private String identificationTypeId;

	public IdentificationTypeId() {
	}

	public IdentificationTypeId(String identificationTypeId) {
		this.identificationTypeId = identificationTypeId;
	}

	public String getIdentificationTypeId() {
		return this.identificationTypeId;
	}

	public void setIdentificationTypeId(String identificationTypeId) {
		this.identificationTypeId = identificationTypeId;
	}

	@Override
	public Object getPk() {
		return this.identificationTypeId;
	}

	@Override
	public void setPk(Object pk) {
		this.identificationTypeId = (String) pk;
	}

	@Override
	public String toString() {
		return "IDENTIFICATION_TYPE_ID:[" + this.getPk().toString() + "]";
	}
}
