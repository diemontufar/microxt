package microxt.entity.common;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractHistoricalLanguageKey extends
		AbstractMultilanguageKey implements Cloneable, HistoricalKey {
	private static final long serialVersionUID = 1L;

	// @Temporal( TemporalType.TIMESTAMP)
	@Column(name = "EXPIRED", unique = true, nullable = false)
	private Timestamp expired;

	public Timestamp getExpired() {
		return this.expired;
	};

	public void setExpired(Timestamp pExpired) {
		this.expired = pExpired;
	};

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	};
}
