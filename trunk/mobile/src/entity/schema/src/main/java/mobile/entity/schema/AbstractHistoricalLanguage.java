package mobile.entity.schema;

import java.sql.Timestamp;

import javax.persistence.Column;

public abstract class AbstractHistoricalLanguage extends AbstractEntity implements Historical, Multilanguage {

	private static final long serialVersionUID = 1L;

	@Column(name = "CREATED", unique = true, nullable = false)
	Timestamp created;

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp pExpired) {
		this.created = pExpired;
	}

}
