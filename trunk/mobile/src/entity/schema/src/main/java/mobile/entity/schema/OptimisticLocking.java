package mobile.entity.schema;

public interface OptimisticLocking extends GeneralEntity {
	public Long getVersion();

	public void setVersion(Long version);
}