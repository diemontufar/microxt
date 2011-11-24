package mobile.entity.schema;

import java.io.Serializable;

public interface GeneralEntity extends Serializable, Cloneable {
	public Object getPk();

	public void setPk(Object pk);

	public Object clone() throws CloneNotSupportedException;

	/*
	 * public void load(Object dto);
	 * 
	 * public Object toDto();
	 */
}
