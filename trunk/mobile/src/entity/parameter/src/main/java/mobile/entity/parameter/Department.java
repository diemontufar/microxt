package mobile.entity.parameter;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;


/**
 * The persistent class for the DEPARTMENT database table.
 * Values of departments
 */
@Entity
@Table(name = "DEPARTMENT")
public class Department extends AbstractEntity implements Multicompany, Multilanguage {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private DepartmentPk pk;

    /**
     * Name of department
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    public Department() {}

    public Department(DepartmentPk pk) {
        this.pk = pk;
    }

    public Department(DepartmentPk pk, String name) {
        this.pk = pk;
        this.name = name;
    }

    public DepartmentPk getPk() {
        return this.pk;
    }

    public void setPk(DepartmentPk pk) {
        this.pk = pk;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (DepartmentPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Department copy = (Department) super.clone();

        copy.setPk((DepartmentPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "DEPARTMENT:[" + this.getPk().toString() + ", " + this.getName()
                + "]";
    }
}
