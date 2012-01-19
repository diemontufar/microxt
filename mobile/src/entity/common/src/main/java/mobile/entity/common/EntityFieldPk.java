package mobile.entity.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import mobile.entity.schema.AbstractCompanyKey;
import mobile.entity.schema.MulticompanyKey;

/**
 * The primary key class for the ENTITY_FIELD database table.
 */
@Embeddable
public class EntityFieldPk extends AbstractCompanyKey implements MulticompanyKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Table Id
	 */
	@Column(name = "TABLE_ID", unique = true, nullable = false)
	private String tableId;

	/**
	 * Field Id
	 */
	@Column(name = "FIELD_ID", unique = true, nullable = false)
	private String fieldId;

	public EntityFieldPk() {
	}

	public EntityFieldPk(String tableId, String fieldId) {
		this.tableId = tableId;
		this.fieldId = fieldId;
	}

	public String getTableId() {
		return this.tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getFieldId() {
		return this.fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getTableId() + ", " + this.getFieldId() + "]";
	}
}
