package mobile.entity.parameter;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the DATAFILE_ID database table.
 */
@Entity
@Table(name = "DATAFILE_ID")
public class DatafileId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Datafile Id
	 */
	@Id
	@Column(name = "DATAFILE_ID", nullable = false)
	private Long datafileId;

	public DatafileId() {
	}

	public DatafileId(Long datafileId) {
		this.datafileId = datafileId;
	}

	public Long getDatafileId() {
		return this.datafileId;
	}

	public void setDatafileId(Long datafileId) {
		this.datafileId = datafileId;
	}

	@Override
	public Object getPk() {
		return this.datafileId;
	}

	@Override
	public void setPk(Object pk) {
		this.datafileId = (Long) pk;
	}

	@Override
	public String toString() {
		return "DATAFILE_ID:[" + this.getPk().toString() + "]";
	}
}
