package mobile.entity.transport;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;


/**
 * The persistent class for the SERVICE_ID database table.
 */
@Entity
@Table(name = "SERVICE_ID")
public class ServiceId extends AbstractEntityId implements GeneralEntityId {
    private static final long serialVersionUID = 1L;

    /**
     * Service Id
     */
    @Id
    @Column(name = "SERVICE_ID", nullable = false)
    private String serviceId;

    public ServiceId() {}

    public ServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public Object getPk() {
        return this.serviceId;
    }

    @Override
    public void setPk(Object pk) {
        this.serviceId = (String) pk;
    }

    @Override
    public String toString() {
        return "SERVICE_ID:[" + this.getPk().toString() + "]";
    }
}
