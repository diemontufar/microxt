package mobile.entity.parameter;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;


/**
 * The persistent class for the BRANCH database table.
 * Values of branches
 */
@Entity
@Table(name = "BRANCH")
public class Branch extends AbstractEntity implements Multicompany {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private BranchPk pk;

    /**
     * Name of branch
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    public Branch() {}

    public Branch(BranchPk pk) {
        this.pk = pk;
    }

    public Branch(BranchPk pk, String name) {
        this.pk = pk;
        this.name = name;
    }

    public BranchPk getPk() {
        return this.pk;
    }

    public void setPk(BranchPk pk) {
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
        this.pk = (BranchPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Branch copy = (Branch) super.clone();

        copy.setPk((BranchPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "BRANCH:[" + this.getPk().toString() + ", " + this.getName()
                + "]";
    }
}
