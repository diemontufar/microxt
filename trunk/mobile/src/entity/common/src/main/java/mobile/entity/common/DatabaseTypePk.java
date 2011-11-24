package mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import mobile.entity.schema.AbstractEntityKey;
import mobile.entity.schema.GeneralEntityKey;

/**
 * The primary key class for the DATABASE_TYPE database table.
 */
@Embeddable
public class DatabaseTypePk extends AbstractEntityKey implements
		GeneralEntityKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Data type Id
	 */
	@Column(name = "DATA_TYPE_ID", nullable = false)
	private String dataTypeId;

	/**
	 * Database Id
	 */
	@Column(name = "DATABASE_ID", nullable = false)
	private String databaseId;

	/**
	 * Data size of field
	 */
	@Column(name = "DATA_SIZE", nullable = false)
	private Integer dataSize;

	public DatabaseTypePk() {
	}

	public DatabaseTypePk(String dataTypeId, String databaseId, Integer dataSize) {
		this.dataTypeId = dataTypeId;
		this.databaseId = databaseId;
		this.dataSize = dataSize;
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

	public Integer getDataSize() {
		return this.dataSize;
	}

	public void setDataSize(Integer dataSize) {
		this.dataSize = dataSize;
	}

	@Override
	public String toString() {
		return "[" + this.getDataTypeId() + ", " + this.getDatabaseId() + ", "
				+ this.getDataSize() + "]";
	}
}
