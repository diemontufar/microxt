package microxt.entity.common;

public interface OptimisticLocking extends GeneralEntity {
	public Long getVersion();

	public void setVersion(Long version);
}