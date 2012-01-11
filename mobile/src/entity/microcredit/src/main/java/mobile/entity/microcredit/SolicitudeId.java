package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the SOLICITUDE_ID database table.
 */
@Entity
@Table(name = "SOLICITUDE_ID")
public class SolicitudeId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Name
	 */
	@Id
	@Column(name = "SOLICITUDE_ID", nullable = false)
	private String solicitudeId;

	public SolicitudeId() {
	}

	public SolicitudeId(String solicitudeId) {
		this.solicitudeId = solicitudeId;
	}

	public String getSolicitudeId() {
		return this.solicitudeId;
	}

	public void setSolicitudeId(String solicitudeId) {
		this.solicitudeId = solicitudeId;
	}

	@Override
	public Object getPk() {
		return this.solicitudeId;
	}

	@Override
	public void setPk(Object pk) {
		this.solicitudeId = (String) pk;
	}

	@Override
	public String toString() {
		return "SOLICITUDE_ID:[" + this.getPk().toString() + "]";
	}
}
