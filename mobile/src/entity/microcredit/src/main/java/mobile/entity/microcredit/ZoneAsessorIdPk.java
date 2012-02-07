package mobile.entity.microcredit;

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
	 * User Id
	 */
	@Column(name = "USER_ID", nullable = false)
	private String userId;

	/**
	 * Geographic zone id
	 */
	@Column(name = "GEOGRAPHIC_ZONE_ID", nullable = false)
	private String geographicZoneId;

	public ZoneAsessorIdPk() {
	}

	public ZoneAsessorIdPk(String userId, String geographicZoneId) {
		this.userId = userId;
		this.geographicZoneId = geographicZoneId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGeographicZoneId() {
		return this.geographicZoneId;
	}

	public void setGeographicZoneId(String geographicZoneId) {
		this.geographicZoneId = geographicZoneId;
	}

	@Override
	public String toString() {
		return "[" + this.getUserId() + ", " + this.getGeographicZoneId() + "]";
	}
}
