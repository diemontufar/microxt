package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;
import mobile.entity.schema.SequentialKey;

/**
 * The primary key class for the GEOGRAPHIC_ZONE database table.
 */
@Embeddable
public class GeographicZonePk extends AbstractCompanyLanguageKey implements MulticompanyKey, MultilanguageKey,
		SequentialKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Geographic zone id
	 */
	@Column(name = "GEOGRAPHIC_ZONE_ID", nullable = false)
	private Integer geographicZoneId;

	public GeographicZonePk() {
	}

	public GeographicZonePk(Integer geographicZoneId) {
		this.geographicZoneId = geographicZoneId;
	}

	public Integer getGeographicZoneId() {
		return this.geographicZoneId;
	}

	public void setGeographicZoneId(Integer geographicZoneId) {
		this.geographicZoneId = geographicZoneId;
	}

	@Override
	public Integer getId() {
		return this.geographicZoneId;
	}

	@Override
	public void setId(Integer sequentialNumber) {
		this.geographicZoneId = sequentialNumber;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getGeographicZoneId() + "]";
	}
}
