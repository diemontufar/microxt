package mobile.entity.zonification;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the ZONE_ASESSOR database table.
 */
@Embeddable
public class ZoneAsessorPk extends AbstractCompanyLanguageKey implements
		MulticompanyKey, MultilanguageKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Geographic zone id
	 */
	@Column(name = "GEOGRAPHIC_ZONE_ID", nullable = false)
	private String geographicZoneId;

	/**
	 * Assessor
	 */
	@Column(name = "ASESSOR_ID", nullable = false)
	private String asessorId;

	public ZoneAsessorPk() {
	}

	public ZoneAsessorPk(String geographicZoneId, String asessorId) {
		this.geographicZoneId = geographicZoneId;
		this.asessorId = asessorId;
	}

	public String getGeographicZoneId() {
		return this.geographicZoneId;
	}

	public void setGeographicZoneId(String geographicZoneId) {
		this.geographicZoneId = geographicZoneId;
	}

	public String getAsessorId() {
		return this.asessorId;
	}

	public void setAsessorId(String asessorId) {
		this.asessorId = asessorId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", "
				+ this.getGeographicZoneId() + ", " + this.getAsessorId() + "]";
	}
}
