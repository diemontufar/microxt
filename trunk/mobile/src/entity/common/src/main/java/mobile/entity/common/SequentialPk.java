package mobile.entity.common;


import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyKey;
import mobile.entity.schema.MulticompanyKey;


/**
 * The primary key class for the SEQUENTIAL database table.
 */
@Embeddable
public class SequentialPk extends AbstractCompanyKey implements MulticompanyKey {
    private static final long serialVersionUID = 1L;

    /**
     * Sequential Id
     */
    @Column(name = "SEQUENTIAL_ID", nullable = false)
    private String sequentialId;

    public SequentialPk() {}

    public SequentialPk(String sequentialId) {
        this.sequentialId = sequentialId;
    }

    public String getSequentialId() {
        return this.sequentialId;
    }

    public void setSequentialId(String sequentialId) {
        this.sequentialId = sequentialId;
    }

    @Override
    public String toString() {
        return "[" + this.getCompanyId() + ", " + this.getSequentialId() + "]";
    }
}
