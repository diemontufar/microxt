package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DATABASE_TYPE database table.
 * 
 */
@javax.persistence.Entity
@Table(name="DATABASE_TYPE")
public class DatabaseType implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DatabaseTypePK id;

	@Column(name="DATABASE_TYPE", nullable=false, length=30)
	private String databaseType;

	//uni-directional many-to-one association to DataType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="DATA_TYPE_ID", nullable=false, insertable=false, updatable=false)
	private DataType dataType;

    public DatabaseType() {
    }

	public DatabaseTypePK getId() {
		return this.id;
	}

	public void setId(DatabaseTypePK id) {
		this.id = id;
	}
	
	public String getDatabaseType() {
		return this.databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	public DataType getDataType() {
		return this.dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}
	
}