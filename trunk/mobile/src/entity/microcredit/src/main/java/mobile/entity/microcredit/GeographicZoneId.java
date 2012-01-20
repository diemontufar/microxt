package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the GEOGRAPHIC_ZONE_ID database table.
 */
@Entity
@Table(name = "GEOGRAPHIC_ZONE_ID")
public class GeographicZoneId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Geographic zone id
	 */
	@Id
	@Column(name = "GEOGRAPHIC_ZONE_ID", nullable = false)
	private String geographicZoneId;

	public GeographicZoneId() {
	}

	public GeographicZoneId(String geographicZoneId) {
		this.geographicZoneId = geographicZoneId;
	}

	public String getGeographicZoneId() {
		return this.geographicZoneId;
	}

	public void setGeographicZoneId(String geographicZoneId) {
		this.geographicZoneId = geographicZoneId;
	}

	@Override
	public Object getPk() {
		return this.geographicZoneId;
	}

	@Override
	public void setPk(Object pk) {
		this.geographicZoneId = (String) pk;
	}

	@Override
	public String toString() {
		return "GEOGRAPHIC_ZONE_ID:[" + this.getPk().toString() + "]";
	}
}
