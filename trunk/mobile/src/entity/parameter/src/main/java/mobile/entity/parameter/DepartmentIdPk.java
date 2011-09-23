package mobile.entity.parameter;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntityKey;
import mobile.entity.schema.GeneralEntityKey;


/**
 * The primary key class for the DEPARTMENT_ID database table.
 */
@Embeddable
public class DepartmentIdPk extends AbstractEntityKey implements GeneralEntityKey {
    private static final long serialVersionUID = 1L;

    /**
     * Branch Id
     */
    @Column(name = "BRANCH_ID", nullable = false)
    private String branchId;

    /**
     * Office Id
     */
    @Column(name = "OFFICE_ID", nullable = false)
    private String officeId;

    /**
     * Department Id
     */
    @Column(name = "DEPARTMENT_ID", nullable = false)
    private String departmentId;

    public DepartmentIdPk() {}

    public DepartmentIdPk(String branchId, String officeId, String departmentId) {
        this.branchId = branchId;
        this.officeId = officeId;
        this.departmentId = departmentId;
    }

    public String getBranchId() {
        return this.branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getOfficeId() {
        return this.officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getDepartmentId() {
        return this.departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "[" + this.getBranchId() + ", " + this.getOfficeId() + ", "
                + this.getDepartmentId() + "]";
    }
}
