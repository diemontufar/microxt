package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;
import mobile.entity.schema.HistoricalKey;
import mobile.entity.schema.SequentialKey;

/**
 * The primary key class for the SOLICITUDE database table.
 */
@Embeddable
public class SolicitudePk extends AbstractCompanyLanguageHistoricalKey implements MulticompanyKey, MultilanguageKey,
		HistoricalKey, SequentialKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Solicitude id
	 */
	@Column(name = "SOLICITUDE_ID", nullable = false)
	private Integer solicitudeId;

	public SolicitudePk() {
	}

	public SolicitudePk(Integer solicitudeId) {
		this.solicitudeId = solicitudeId;
	}

	public Integer getSolicitudeId() {
		return this.solicitudeId;
	}

	public void setSolicitudeId(Integer solicitudeId) {
		this.solicitudeId = solicitudeId;
	}

	@Override
	public Integer getId() {
		return this.solicitudeId;
	}

	@Override
	public void setId(Integer sequentialNumber) {
		this.solicitudeId = sequentialNumber;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getExpired() + ", "
				+ this.getSolicitudeId() + "]";
	}
}
