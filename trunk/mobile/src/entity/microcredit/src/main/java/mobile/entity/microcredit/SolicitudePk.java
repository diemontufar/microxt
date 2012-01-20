package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;
import mobile.entity.schema.HistoricalKey;

/**
 * The primary key class for the SOLICITUDE database table.
 */
@Embeddable
public class SolicitudePk extends AbstractCompanyLanguageHistoricalKey implements MulticompanyKey, MultilanguageKey,
		HistoricalKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Name
	 */
	@Column(name = "SOLICITUDE_ID", nullable = false)
	private String solicitudeId;

	public SolicitudePk() {
	}

	public SolicitudePk(String solicitudeId) {
		this.solicitudeId = solicitudeId;
	}

	public String getSolicitudeId() {
		return this.solicitudeId;
	}

	public void setSolicitudeId(String solicitudeId) {
		this.solicitudeId = solicitudeId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getExpired() + ", "
				+ this.getSolicitudeId() + "]";
	}
}
