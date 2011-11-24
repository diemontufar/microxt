package mobile.entity.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the CIVIL_STATUS_ID database table.
 */
@Entity
@Table(name = "CIVIL_STATUS_ID")
public class CivilStatusId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Civil type status Id
	 */
	@Id
	@Column(name = "CIVIL_STATUS_ID", nullable = false)
	private String civilStatusId;

	public CivilStatusId() {
	}

	public CivilStatusId(String civilStatusId) {
		this.civilStatusId = civilStatusId;
	}

	public String getCivilStatusId() {
		return this.civilStatusId;
	}

	public void setCivilStatusId(String civilStatusId) {
		this.civilStatusId = civilStatusId;
	}

	@Override
	public Object getPk() {
		return this.civilStatusId;
	}

	@Override
	public void setPk(Object pk) {
		this.civilStatusId = (String) pk;
	}

	@Override
	public String toString() {
		return "CIVIL_STATUS_ID:[" + this.getPk().toString() + "]";
	}
}
