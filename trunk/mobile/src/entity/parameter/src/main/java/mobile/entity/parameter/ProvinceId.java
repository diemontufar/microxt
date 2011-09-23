package mobile.entity.parameter;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;


/**
 * The persistent class for the PROVINCE_ID database table.
 */
@Entity
@Table(name = "PROVINCE_ID")
public class ProvinceId extends AbstractEntityId implements GeneralEntityId {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ProvinceIdPk pk;

    public ProvinceId() {}

    public ProvinceId(ProvinceIdPk pk) {
        this.pk = pk;
    }

    public ProvinceIdPk getPk() {
        return this.pk;
    }

    public void setPk(ProvinceIdPk pk) {
        this.pk = pk;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (ProvinceIdPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ProvinceId copy = (ProvinceId) super.clone();

        copy.setPk((ProvinceIdPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "PROVINCE_ID:[" + this.getPk().toString() + "]";
    }
}
