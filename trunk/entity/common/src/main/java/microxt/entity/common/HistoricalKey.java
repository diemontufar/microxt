package microxt.entity.common;

import java.sql.Timestamp;

public interface HistoricalKey extends GeneralKey {
	public Timestamp getExpired();

	public void setExpired(Timestamp expired);
}
