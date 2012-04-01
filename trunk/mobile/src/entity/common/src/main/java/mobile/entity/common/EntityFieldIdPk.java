package mobile.entity.common;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityKey;
import mobile.entity.schema.GeneralEntityKey;

/**
* The primary key class for the ENTITY_FIELD_ID database table.
*/
@Embeddable
public class EntityFieldIdPk extends AbstractEntityKey implements GeneralEntityKey{
private static final long serialVersionUID = 1L;

/**
* Table Id
*/
@Column(name="TABLE_ID", unique=true, nullable=false)
private String tableId;

/**
* Field Id
*/
@Column(name="FIELD_ID", unique=true, nullable=false)
private String fieldId;

public EntityFieldIdPk() {
}
public EntityFieldIdPk(String tableId,String fieldId) {
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
return "[" +
this.getTableId() + ", " +
this.getFieldId() + "]";
}
}
