package mobile.entity.person;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;


/**
 * The persistent class for the PHONE_TYPE database table.
 * Values of phone types
 */
@Entity
@Table(name = "PHONE_TYPE")
public class PhoneType extends AbstractEntity implements Multicompany, Multilanguage {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PhoneTypePk pk;

    /**
     * Name of phone
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    public PhoneType() {}

    public PhoneType(PhoneTypePk pk) {
        this.pk = pk;
    }

    public PhoneType(PhoneTypePk pk, String name) {
        this.pk = pk;
        this.name = name;
    }

    public PhoneTypePk getPk() {
        return this.pk;
    }

    public void setPk(PhoneTypePk pk) {
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
        this.pk = (PhoneTypePk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        PhoneType copy = (PhoneType) super.clone();

        copy.setPk((PhoneTypePk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "PHONE_TYPE:[" + this.getPk().toString() + ", " + this.getName()
                + "]";
    }
}
