package microxt.entity.entity;

import javax.persistence.Column;
import javax.persistence.Id;

import microxt.entity.common.AbstractEntity;
import microxt.entity.common.GeneralEntity;


/**
 * The persistent class for the DATA_TYPE database table.
 */
//@Entity
//@Table(name="DATA_TYPE")
public class DataType extends AbstractEntity implements GeneralEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DATA_TYPE_ID", unique=true, nullable=false, length=30)
	private String dataTypeId;

	@Column(name="DATABASE_NAME", length=30)
	private String databaseName;

	@Column(name="DATABASE_VALUE", length=30)
	private String databaseValue;

    public DataType() {
    }

	public String getDataTypeId() {
		return this.dataTypeId;
	}

	public void setDataTypeId(String dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	public String getDatabaseName() {
		return this.databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getDatabaseValue() {
		return this.databaseValue;
	}

	public void setDatabaseValue(String databaseValue) {
		this.databaseValue = databaseValue;
	}

	public Object getPk() {
		return this.getDataTypeId();
	}

	public void setPk(Object pk) {
		this.setDataTypeId((String)pk);
	}

	@Override
	public String toString() {
		return "[" + this.getDataTypeId() + ", " +
			this.getDatabaseValue() + ", " +
			this.getDatabaseValue() + "]";
	}

}