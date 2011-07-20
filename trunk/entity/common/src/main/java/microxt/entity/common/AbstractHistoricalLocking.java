package microxt.entity.common;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractHistoricalLocking extends
		AbstractOptimisticLocking implements Historical {
	private static final long serialVersionUID = 1L;

	@Column(name = "CREATED", unique = true, nullable = false)
	Timestamp created;

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp pCreated) {
		this.created = pCreated;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
