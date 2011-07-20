package microxt.entity.common;

import java.io.Serializable;

public interface GeneralKey extends Serializable, Cloneable {
	public Object clone() throws CloneNotSupportedException;
}
