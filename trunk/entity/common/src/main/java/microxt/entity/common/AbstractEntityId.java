package microxt.entity.common;

public abstract class AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	public abstract Class<?> getEntityDataClass();

	public abstract Object getEntityDataPk() throws Exception;

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	};

	public abstract String toString();
}
