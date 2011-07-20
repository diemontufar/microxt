package microxt.entity.common;

import java.io.Serializable;

public interface GeneralEntity extends Serializable {
	public Object getPk();

	public void setPk(Object pk);
	
	public Object clone() throws CloneNotSupportedException;

	/*
	public void load(Object dto);

	public Object toDto();
	*/
}
