package mobile.entity.schema;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntityKey implements GeneralEntityKey {
	private static final long serialVersionUID = 1L;

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public abstract String toString();
}
