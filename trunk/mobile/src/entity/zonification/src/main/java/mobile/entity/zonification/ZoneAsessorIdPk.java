package mobile.entity.zonification;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityKey;
import mobile.entity.schema.GeneralEntityKey;

/**
 * The primary key class for the ZONE_ASESSOR_ID database table.
 */
@Embeddable
public class ZoneAsessorIdPk extends AbstractEntityKey implements
		GeneralEntityKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Assessor
	 */
	@Column(name = "ASESSOR_ID", nullable = false)
	private String asessorId;

	/**
	 * Geographic zone id
	 */
	@Column(name = "GEOGRAPHIC_ZONE_ID", nullable = false)
	private String geographicZoneId;

	public ZoneAsessorIdPk() {
	}

	public ZoneAsessorIdPk(String asessorId, String geographicZoneId) {
		this.asessorId = asessorId;
		this.geographicZoneId = geographicZoneId;
	}

	public String getAsessorId() {
		return this.asessorId;
	}

	public void setAsessorId(String asessorId) {
		this.asessorId = asessorId;
	}

	public String getGeographicZoneId() {
		return this.geographicZoneId;
	}

	public void setGeographicZoneId(String geographicZoneId) {
		this.geographicZoneId = geographicZoneId;
	}

	@Override
	public String toString() {
		return "[" + this.getAsessorId() + ", " + this.getGeographicZoneId()
				+ "]";
	}
}
