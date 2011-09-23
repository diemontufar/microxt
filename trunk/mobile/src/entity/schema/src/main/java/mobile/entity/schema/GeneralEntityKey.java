package mobile.entity.schema;

import java.io.Serializable;

public interface GeneralEntityKey extends Serializable, Cloneable {
	public Object clone() throws CloneNotSupportedException;
}
