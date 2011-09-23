package mobile.entity.common;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;


/**
 * The persistent class for the SEQUENTIAL_ID database table.
 */
@Entity
@Table(name = "SEQUENTIAL_ID")
public class SequentialId extends AbstractEntityId implements GeneralEntityId {
    private static final long serialVersionUID = 1L;

    /**
     * Sequential Id
     */
    @Id
    @Column(name = "SEQUENTIAL_ID", nullable = false)
    private String sequentialId;

    public SequentialId() {}

    public SequentialId(String sequentialId) {
        this.sequentialId = sequentialId;
    }

    public String getSequentialId() {
        return this.sequentialId;
    }

    public void setSequentialId(String sequentialId) {
        this.sequentialId = sequentialId;
    }

    @Override
    public Object getPk() {
        return this.sequentialId;
    }

    @Override
    public void setPk(Object pk) {
        this.sequentialId = (String) pk;
    }

    @Override
    public String toString() {
        return "SEQUENTIAL_ID:[" + this.getPk().toString() + "]";
    }
}
