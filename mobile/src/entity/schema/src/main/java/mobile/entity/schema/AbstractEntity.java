package mobile.entity.schema;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntity implements GeneralEntity {
	private static final long serialVersionUID = 1L;

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public abstract String toString();
}
