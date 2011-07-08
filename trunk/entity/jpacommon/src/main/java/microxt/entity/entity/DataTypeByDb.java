package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;

import microxt.entity.common.AbstractEntity;
import microxt.entity.common.GeneralEntity;


/**
 * The persistent class for the DATA_TYPE_BY_DB database table.
 * 
 */
@javax.persistence.Entity
@Table(name="DATA_TYPE_BY_DB")
public class DataTypeByDb extends AbstractEntity implements Serializable, GeneralEntity {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DataTypeByDbPK pk;

	@Column(name="DATA_BASE_VALUE")
	private String dataBaseValue;

    public DataTypeByDb() {
    }

	public DataTypeByDbPK getPk() {
		return this.pk;
	}

	public void setPk(DataTypeByDbPK pk) {
		this.pk = pk;
	}
	
	public String getDataBaseValue() {
		return this.dataBaseValue;
	}

	public void setDataBaseValue(String dataBaseValue) {
		this.dataBaseValue = dataBaseValue;
	}

	@Override
	public void setPk(Object pk) {
		setPk((DataTypeByDbPK)pk);
	}

	@Override
	public String toString() {
		return "[" + 
		this.getPk().toString() + ", " +
		this.getDataBaseValue()
		+ "]";
		
	}

}