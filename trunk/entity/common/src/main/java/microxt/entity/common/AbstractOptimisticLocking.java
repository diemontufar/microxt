package microxt.entity.common;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractOptimisticLocking extends AbstractEntity
		implements OptimisticLocking {
	private static final long serialVersionUID = 1L;

	@Version
	@Column(name = "VERSION", precision = 10)
	private Long version;

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
