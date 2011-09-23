package mobile.entity.parameter;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;


/**
 * The persistent class for the CITY_ID database table.
 */
@Entity
@Table(name = "CITY_ID")
public class CityId extends AbstractEntityId implements GeneralEntityId {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CityIdPk pk;

    public CityId() {}

    public CityId(CityIdPk pk) {
        this.pk = pk;
    }

    public CityIdPk getPk() {
        return this.pk;
    }

    public void setPk(CityIdPk pk) {
        this.pk = pk;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (CityIdPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        CityId copy = (CityId) super.clone();

        copy.setPk((CityIdPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "CITY_ID:[" + this.getPk().toString() + "]";
    }
}
