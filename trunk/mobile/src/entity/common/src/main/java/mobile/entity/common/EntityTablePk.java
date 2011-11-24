package mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import mobile.entity.schema.AbstractCompanyKey;
import mobile.entity.schema.MulticompanyKey;

/**
 * The primary key class for the ENTITY_TABLE database table.
 */
@Embeddable
public class EntityTablePk extends AbstractCompanyKey implements
		MulticompanyKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Table Id
	 */
	@Column(name = "TABLE_ID", nullable = false)
	private String tableId;

	public EntityTablePk() {
	}

	public EntityTablePk(String tableId) {
		this.tableId = tableId;
	}

	public String getTableId() {
		return this.tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getTableId() + "]";
	}
}
