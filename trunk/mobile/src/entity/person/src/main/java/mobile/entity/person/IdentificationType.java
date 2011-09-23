package mobile.entity.person;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;


/**
 * The persistent class for the IDENTIFICATION_TYPE database table.
 * Values of identification types
 */
@Entity
@Table(name = "IDENTIFICATION_TYPE")
public class IdentificationType extends AbstractEntity implements Multicompany, Multilanguage {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private IdentificationTypePk pk;

    /**
     * Name of identification type
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    public IdentificationType() {}

    public IdentificationType(IdentificationTypePk pk) {
        this.pk = pk;
    }

    public IdentificationType(IdentificationTypePk pk, String name) {
        this.pk = pk;
        this.name = name;
    }

    public IdentificationTypePk getPk() {
        return this.pk;
    }

    public void setPk(IdentificationTypePk pk) {
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
        this.pk = (IdentificationTypePk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        IdentificationType copy = (IdentificationType) super.clone();

        copy.setPk((IdentificationTypePk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "IDENTIFICATION_TYPE:[" + this.getPk().toString() + ", "
                + this.getName() + "]";
    }
}
