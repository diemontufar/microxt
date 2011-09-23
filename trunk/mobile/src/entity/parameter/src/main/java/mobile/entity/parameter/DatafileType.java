package mobile.entity.parameter;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.GeneralEntity;


/**
 * The persistent class for the DATAFILE_TYPE database table.
 * Values of datafile types
 */
@Entity
@Table(name = "DATAFILE_TYPE")
public class DatafileType extends AbstractEntity implements GeneralEntity {
    private static final long serialVersionUID = 1L;

    /**
     * Datafile type Id
     */
    @Id
    @Column(name = "DATAFILE_TYPE_ID", nullable = false)
    private String datafileTypeId;

    /**
     * Name of datafile type
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    public DatafileType() {}

    public DatafileType(String datafileTypeId) {
        this.datafileTypeId = datafileTypeId;
    }

    public DatafileType(String datafileTypeId, String name) {
        this.datafileTypeId = datafileTypeId;
        this.name = name;
    }

    public String getDatafileTypeId() {
        return this.datafileTypeId;
    }

    public void setDatafileTypeId(String datafileTypeId) {
        this.datafileTypeId = datafileTypeId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object getPk() {
        return this.datafileTypeId;
    }

    @Override
    public void setPk(Object pk) {
        this.datafileTypeId = (String) pk;
    }

    @Override
    public String toString() {
        return "DATAFILE_TYPE:[" + this.getPk().toString() + ", "
                + this.getName() + "]";
    }
}
