package mobile.entity.parameter;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;


/**
 * The persistent class for the PROVINCE database table.
 * Values of provinces
 */
@Entity
@Table(name = "PROVINCE")
public class Province extends AbstractEntity implements Multicompany, Multilanguage {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ProvincePk pk;

    /**
     * Name of province
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    public Province() {}

    public Province(ProvincePk pk) {
        this.pk = pk;
    }

    public Province(ProvincePk pk, String name) {
        this.pk = pk;
        this.name = name;
    }

    public ProvincePk getPk() {
        return this.pk;
    }

    public void setPk(ProvincePk pk) {
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
        this.pk = (ProvincePk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Province copy = (Province) super.clone();

        copy.setPk((ProvincePk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "PROVINCE:[" + this.getPk().toString() + ", " + this.getName()
                + "]";
    }
}
