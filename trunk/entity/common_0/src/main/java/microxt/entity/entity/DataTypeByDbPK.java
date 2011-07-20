package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;

import microxt.entity.common.AbstractEntity;
import microxt.entity.common.AbstractKey;
import microxt.entity.common.GeneralKey;

/**
 * The primary key class for the DATA_TYPE_BY_DB database table.
 * 
 */
@Embeddable
public class DataTypeByDbPK extends AbstractKey implements Serializable, GeneralKey {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DATA_TYPE_ID")
	private String dataTypeId;

	@Column(name="DATA_BASE")
	private String dataBase;

    public DataTypeByDbPK() {
    }
	public String getDataTypeId() {
		return this.dataTypeId;
	}
	public void setDataTypeId(String dataTypeId) {
		this.dataTypeId = dataTypeId;
	}
	public String getDataBase() {
		return this.dataBase;
	}
	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DataTypeByDbPK)) {
			return false;
		}
		DataTypeByDbPK castOther = (DataTypeByDbPK)other;
		return 
			this.dataTypeId.equals(castOther.dataTypeId)
			&& this.dataBase.equals(castOther.dataBase);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.dataTypeId.hashCode();
		hash = hash * prime + this.dataBase.hashCode();
		
		return hash;
    }
	@Override
	public String toString() {
		return "[" + 
		this.getDataTypeId()+ ", " +
		this.getDataBase()
		+ "]";

	}
}