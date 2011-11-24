package mobile.entity.schema;

import java.sql.Timestamp;

public interface HistoricalKey extends GeneralEntityKey {
	public Timestamp getExpired();

	public void setExpired(Timestamp expired);
}
