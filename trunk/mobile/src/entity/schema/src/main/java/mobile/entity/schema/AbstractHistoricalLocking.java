package mobile.entity.schema;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractHistoricalLocking implements Historical, OptimisticLocking {
	private static final long serialVersionUID = 1L;

	@Column(name = "CREATED", unique = true, nullable = false)
	Timestamp created;
	
	@Version
	@Column(name = "VERSION", precision = 10)
	private Long version;

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp pCreated) {
		this.created = pCreated;
	}
	
	public Long getVersion() {
		return this.version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
