package mobile.entity.person;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;


/**
 * The persistent class for the ADDRESS_TYPE_ID database table.
 */
@Entity
@Table(name = "ADDRESS_TYPE_ID")
public class AddressTypeId extends AbstractEntityId implements GeneralEntityId {
    private static final long serialVersionUID = 1L;

    /**
     * Address type Id
     */
    @Id
    @Column(name = "ADDRESS_TYPE_ID", nullable = false)
    private String addressTypeId;

    public AddressTypeId() {}

    public AddressTypeId(String addressTypeId) {
        this.addressTypeId = addressTypeId;
    }

    public String getAddressTypeId() {
        return this.addressTypeId;
    }

    public void setAddressTypeId(String addressTypeId) {
        this.addressTypeId = addressTypeId;
    }

    @Override
    public Object getPk() {
        return this.addressTypeId;
    }

    @Override
    public void setPk(Object pk) {
        this.addressTypeId = (String) pk;
    }

    @Override
    public String toString() {
        return "ADDRESS_TYPE_ID:[" + this.getPk().toString() + "]";
    }
}
