package microxt.entity.common;

public interface GeneralEntityId extends GeneralEntity {
	
	public Class<?> getEntityDataClass();

	public Object getEntityDataPk() throws Exception;

	public Object clone() throws CloneNotSupportedException;

	public String toString();
}
