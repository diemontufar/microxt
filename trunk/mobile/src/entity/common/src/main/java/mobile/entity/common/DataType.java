package mobile.entity.common;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.GeneralEntity;


/**
 * The persistent class for the DATA_TYPE database table.
 * Values of data types
 */
@Entity
@Table(name = "DATA_TYPE")
public class DataType extends AbstractEntity implements GeneralEntity {
    private static final long serialVersionUID = 1L;

    /**
     * Data type Id
     */
    @Id
    @Column(name = "DATA_TYPE_ID", nullable = false)
    private String dataTypeId;

    /**
     * Name of data type
     */
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    public DataType() {}

    public DataType(String dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public DataType(String dataTypeId, String description) {
        this.dataTypeId = dataTypeId;
        this.description = description;
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

    @Override
    public Object getPk() {
        return this.dataTypeId;
    }

    @Override
    public void setPk(Object pk) {
        this.dataTypeId = (String) pk;
    }

    @Override
    public String toString() {
        return "DATA_TYPE:[" + this.getPk().toString() + ", "
                + this.getDescription() + "]";
    }
}
