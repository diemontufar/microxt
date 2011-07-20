package microxt.entity.common;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractKey implements GeneralKey {
	private static final long serialVersionUID = 1L;

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public abstract String toString();
}
