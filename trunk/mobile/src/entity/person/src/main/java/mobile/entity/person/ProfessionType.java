package mobile.entity.person;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;


/**
 * The persistent class for the PROFESSION_TYPE database table.
 * Values of profession types
 */
@Entity
@Table(name = "PROFESSION_TYPE")
public class ProfessionType extends AbstractEntity implements Multicompany, Multilanguage {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ProfessionTypePk pk;

    /**
     * Name of profession type
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    public ProfessionType() {}

    public ProfessionType(ProfessionTypePk pk) {
        this.pk = pk;
    }

    public ProfessionType(ProfessionTypePk pk, String name) {
        this.pk = pk;
        this.name = name;
    }

    public ProfessionTypePk getPk() {
        return this.pk;
    }

    public void setPk(ProfessionTypePk pk) {
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
        this.pk = (ProfessionTypePk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ProfessionType copy = (ProfessionType) super.clone();

        copy.setPk((ProfessionTypePk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "PROFESSION_TYPE:[" + this.getPk().toString() + ", "
                + this.getName() + "]";
    }
}
