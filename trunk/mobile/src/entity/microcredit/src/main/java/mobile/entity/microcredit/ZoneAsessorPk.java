package mobile.entity.microcredit;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import mobile.entity.schema.AbstractEntityKey;
import mobile.entity.schema.GeneralEntityKey;

/**
 * The primary key class for the ZONE_ASESSOR database table.
 */
@Embeddable
public class ZoneAsessorPk extends AbstractEntityKey implements
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
	private Integer geographicZoneId;

	public ZoneAsessorPk() {
	}

	public ZoneAsessorPk(String userId, Integer geographicZoneId) {
		this.userId = userId;
		this.geographicZoneId = geographicZoneId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getGeographicZoneId() {
		return this.geographicZoneId;
	}

	public void setGeographicZoneId(Integer geographicZoneId) {
		this.geographicZoneId = geographicZoneId;
	}

	@Override
	public String toString() {
		return "[" + this.getUserId() + ", " + this.getGeographicZoneId() + "]";
	}
}
