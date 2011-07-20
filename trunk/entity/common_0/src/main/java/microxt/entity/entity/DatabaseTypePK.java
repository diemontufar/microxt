package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the DATABASE_TYPE database table.
 * 
 */
@Embeddable
public class DatabaseTypePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DATA_TYPE_ID", unique=true, nullable=false, length=30)
	private String dataTypeId;

	@Column(name="DATABASE_ID", unique=true, nullable=false, length=30)
	private String databaseId;

	@Column(name="DATA_SIZE", unique=true, nullable=false, precision=10)
	private long dataSize;

    public DatabaseTypePK() {
    }
	public String getDataTypeId() {
		return this.dataTypeId;
	}
	public void setDataTypeId(String dataTypeId) {
		this.dataTypeId = dataTypeId;
	}
	public String getDatabaseId() {
		return this.databaseId;
	}
	public void setDatabaseId(String databaseId) {
		this.databaseId = databaseId;
	}
	public long getDataSize() {
		return this.dataSize;
	}
	public void setDataSize(long dataSize) {
		this.dataSize = dataSize;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DatabaseTypePK)) {
			return false;
		}
		DatabaseTypePK castOther = (DatabaseTypePK)other;
		return 
			this.dataTypeId.equals(castOther.dataTypeId)
			&& this.databaseId.equals(castOther.databaseId)
			&& (this.dataSize == castOther.dataSize);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.dataTypeId.hashCode();
		hash = hash * prime + this.databaseId.hashCode();
		hash = hash * prime + ((int) (this.dataSize ^ (this.dataSize >>> 32)));
		
		return hash;
    }
}