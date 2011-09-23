package mobile.entity.parameter;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;


/**
 * The persistent class for the OFFICE_ID database table.
 */
@Entity
@Table(name = "OFFICE_ID")
public class OfficeId extends AbstractEntityId implements GeneralEntityId {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OfficeIdPk pk;

    public OfficeId() {}

    public OfficeId(OfficeIdPk pk) {
        this.pk = pk;
    }

    public OfficeIdPk getPk() {
        return this.pk;
    }

    public void setPk(OfficeIdPk pk) {
        this.pk = pk;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (OfficeIdPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        OfficeId copy = (OfficeId) super.clone();

        copy.setPk((OfficeIdPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "OFFICE_ID:[" + this.getPk().toString() + "]";
    }
}
