package mobile.entity.person;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multilanguage;


/**
 * The persistent class for the GENDER_TYPE database table.
 * Values of gender types
 */
@Entity
@Table(name = "GENDER_TYPE")
public class GenderType extends AbstractEntity implements Multilanguage {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private GenderTypePk pk;

    /**
     * Name of gender
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    public GenderType() {}

    public GenderType(GenderTypePk pk) {
        this.pk = pk;
    }

    public GenderType(GenderTypePk pk, String name) {
        this.pk = pk;
        this.name = name;
    }

    public GenderTypePk getPk() {
        return this.pk;
    }

    public void setPk(GenderTypePk pk) {
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
        this.pk = (GenderTypePk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        GenderType copy = (GenderType) super.clone();

        copy.setPk((GenderTypePk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "GENDER_TYPE:[" + this.getPk().toString() + ", " + this.getName()
                + "]";
    }
}
