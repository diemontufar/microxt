package mobile.entity.person;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;


/**
 * The persistent class for the PROFESSION_TYPE_ID database table.
 */
@Entity
@Table(name = "PROFESSION_TYPE_ID")
public class ProfessionTypeId extends AbstractEntityId implements GeneralEntityId {
    private static final long serialVersionUID = 1L;

    /**
     * Profession type Id
     */
    @Id
    @Column(name = "PROFESSION_TYPE_ID", nullable = false)
    private String professionTypeId;

    public ProfessionTypeId() {}

    public ProfessionTypeId(String professionTypeId) {
        this.professionTypeId = professionTypeId;
    }

    public String getProfessionTypeId() {
        return this.professionTypeId;
    }

    public void setProfessionTypeId(String professionTypeId) {
        this.professionTypeId = professionTypeId;
    }

    @Override
    public Object getPk() {
        return this.professionTypeId;
    }

    @Override
    public void setPk(Object pk) {
        this.professionTypeId = (String) pk;
    }

    @Override
    public String toString() {
        return "PROFESSION_TYPE_ID:[" + this.getPk().toString() + "]";
    }
}
