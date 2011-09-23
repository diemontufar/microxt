package mobile.entity.parameter;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;


/**
 * The persistent class for the BRANCH_ID database table.
 */
@Entity
@Table(name = "BRANCH_ID")
public class BranchId extends AbstractEntityId implements GeneralEntityId {
    private static final long serialVersionUID = 1L;

    /**
     * Branch Id
     */
    @Id
    @Column(name = "BRANCH_ID", nullable = false)
    private String branchId;

    public BranchId() {}

    public BranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchId() {
        return this.branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    @Override
    public Object getPk() {
        return this.branchId;
    }

    @Override
    public void setPk(Object pk) {
        this.branchId = (String) pk;
    }

    @Override
    public String toString() {
        return "BRANCH_ID:[" + this.getPk().toString() + "]";
    }
}
