package microxt.entity.common;

import java.sql.Timestamp;

public interface Historical extends GeneralEntity {
	public void setPk(HistoricalKey pk);

	public HistoricalKey getPk();

	public Timestamp getCreated();

	public void setCreated(Timestamp created);
}
