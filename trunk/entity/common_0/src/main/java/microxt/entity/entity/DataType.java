package microxt.entity.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DATA_TYPE database table.
 * 
 */
@javax.persistence.Entity
@Table(name="DATA_TYPE")
public class DataType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DATA_TYPE_ID", unique=true, nullable=false, length=30)
	private String dataTypeId;

	@Column(name="DESCRIPTION", length=100)
	private String description;

    public DataType() {
    }

	public String getDataTypeId() {
		return this.dataTypeId;
	}

	public void setDataTypeId(String dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}