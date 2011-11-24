package mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the ENTITY_TABLE_ID database table.
 */
@Entity
@Table(name = "ENTITY_TABLE_ID")
public class EntityTableId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Table Id
	 */
	@Id
	@Column(name = "TABLE_ID", nullable = false)
	private String tableId;

	public EntityTableId() {
	}

	public EntityTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getTableId() {
		return this.tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	@Override
	public Object getPk() {
		return this.tableId;
	}

	@Override
	public void setPk(Object pk) {
		this.tableId = (String) pk;
	}

	@Override
	public String toString() {
		return "ENTITY_TABLE_ID:[" + this.getPk().toString() + "]";
	}
}
