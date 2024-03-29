package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;
import mobile.entity.schema.HistoricalKey;

/**
 * The primary key class for the RECOMMENDATION database table.
 */
@Embeddable
public class RecommendationPk extends AbstractCompanyLanguageHistoricalKey implements MulticompanyKey,
		MultilanguageKey, HistoricalKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Solicitude Id
	 */
	@Column(name = "SOLICITUDE_ID", nullable = false)
	private Integer solicitudeId;

	public RecommendationPk() {
	}

	public RecommendationPk(Integer solicitudeId) {
		this.solicitudeId = solicitudeId;
	}

	public Integer getSolicitudeId() {
		return this.solicitudeId;
	}

	public void setSolicitudeId(Integer solicitudeId) {
		this.solicitudeId = solicitudeId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getExpired() + ", "
				+ this.getSolicitudeId() + "]";
	}
}
