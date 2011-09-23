package mobile.entity.person;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;


/**
 * The persistent class for the PHONE_TYPE_ID database table.
 */
@Entity
@Table(name = "PHONE_TYPE_ID")
public class PhoneTypeId extends AbstractEntityId implements GeneralEntityId {
    private static final long serialVersionUID = 1L;

    /**
     * Phone type Id
     */
    @Id
    @Column(name = "PHONE_TYPE_ID", nullable = false)
    private String phoneTypeId;

    public PhoneTypeId() {}

    public PhoneTypeId(String phoneTypeId) {
        this.phoneTypeId = phoneTypeId;
    }

    public String getPhoneTypeId() {
        return this.phoneTypeId;
    }

    public void setPhoneTypeId(String phoneTypeId) {
        this.phoneTypeId = phoneTypeId;
    }

    @Override
    public Object getPk() {
        return this.phoneTypeId;
    }

    @Override
    public void setPk(Object pk) {
        this.phoneTypeId = (String) pk;
    }

    @Override
    public String toString() {
        return "PHONE_TYPE_ID:[" + this.getPk().toString() + "]";
    }
}
