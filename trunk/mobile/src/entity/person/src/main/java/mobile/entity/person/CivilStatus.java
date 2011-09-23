package mobile.entity.person;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;


/**
 * The persistent class for the CIVIL_STATUS database table.
 * Values of civil statuses
 */
@Entity
@Table(name = "CIVIL_STATUS")
public class CivilStatus extends AbstractEntity implements Multicompany, Multilanguage {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CivilStatusPk pk;

    /**
     * Name of civil status
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    public CivilStatus() {}

    public CivilStatus(CivilStatusPk pk) {
        this.pk = pk;
    }

    public CivilStatus(CivilStatusPk pk, String name) {
        this.pk = pk;
        this.name = name;
    }

    public CivilStatusPk getPk() {
        return this.pk;
    }

    public void setPk(CivilStatusPk pk) {
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
        this.pk = (CivilStatusPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        CivilStatus copy = (CivilStatus) super.clone();

        copy.setPk((CivilStatusPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "CIVIL_STATUS:[" + this.getPk().toString() + ", "
                + this.getName() + "]";
    }
}
