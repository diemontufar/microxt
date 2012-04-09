package mobile.entity.schema;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractLanguageHistoricalKey extends AbstractEntityKey implements HistoricalKey {
	private static final long serialVersionUID = 1L;

	// @Temporal( TemporalType.TIMESTAMP)
	@Column(name = "EXPIRED", unique = true, nullable = false)
	private Timestamp expired;

	public Timestamp getCreated() {
		return this.expired;
	};

	public void setCreated(Timestamp pExpired) {
		this.expired = pExpired;
	};

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	};
}
