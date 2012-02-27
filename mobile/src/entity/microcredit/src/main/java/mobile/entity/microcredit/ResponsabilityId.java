package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the RESPONSABILITY_ID database table.
 */
@Entity
@Table(name = "RESPONSABILITY_ID")
public class ResponsabilityId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Responsability id
	 */
	@Id
	@Column(name = "RESPONSABILITY_ID", nullable = false)
	private String responsabilityId;

	public ResponsabilityId() {
	}

	public ResponsabilityId(String responsabilityId) {
		this.responsabilityId = responsabilityId;
	}

	public String getResponsabilityId() {
		return this.responsabilityId;
	}

	public void setResponsabilityId(String responsabilityId) {
		this.responsabilityId = responsabilityId;
	}

	@Override
	public Object getPk() {
		return this.responsabilityId;
	}

	@Override
	public void setPk(Object pk) {
		this.responsabilityId = (String) pk;
	}

	@Override
	public String toString() {
		return "RESPONSABILITY_ID:[" + this.getPk().toString() + "]";
	}
}
