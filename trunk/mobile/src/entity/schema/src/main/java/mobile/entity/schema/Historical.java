package mobile.entity.schema;

import java.sql.Timestamp;

public interface Historical extends GeneralEntity {
	public void setPk(Object pk);

	public Object getPk();

	public Timestamp getCreated();

	public void setCreated(Timestamp created);
}
