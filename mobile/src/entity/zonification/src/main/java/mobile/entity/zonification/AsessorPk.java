package mobile.entity.zonification;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;
import mobile.entity.schema.HistoricalKey;

/**
 * The primary key class for the ASESSOR database table.
 */
@Embeddable
public class AsessorPk extends AbstractCompanyLanguageHistoricalKey implements
		MulticompanyKey, MultilanguageKey, HistoricalKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Assessor
	 */
	@Column(name = "ASESSOR_ID", nullable = false)
	private String asessorId;

	public AsessorPk() {
	}

	public AsessorPk(String asessorId) {
		this.asessorId = asessorId;
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
				+ this.getExpired() + ", " + this.getAsessorId() + "]";
	}
}
