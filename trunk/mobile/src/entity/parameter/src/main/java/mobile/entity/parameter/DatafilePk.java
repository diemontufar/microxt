package mobile.entity.parameter;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.HistoricalKey;

/**
 * The primary key class for the DATAFILE database table.
 */
@Embeddable
public class DatafilePk extends AbstractCompanyHistoricalKey implements MulticompanyKey, HistoricalKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Datafile Id
	 */
	@Column(name = "DATAFILE_ID", nullable = false)
	private Long datafileId;

	public DatafilePk() {
	}

	public DatafilePk(Long datafileId) {
		this.datafileId = datafileId;
	}

	public Long getDatafileId() {
		return this.datafileId;
	}

	public void setDatafileId(Long datafileId) {
		this.datafileId = datafileId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getExpired() + ", " + this.getDatafileId() + "]";
	}
}
