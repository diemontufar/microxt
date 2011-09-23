package mobile.entity.transport;


import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.HistoricalKey;


/**
 * The primary key class for the SERVICE database table.
 */
@Embeddable
public class ServicePk extends AbstractCompanyHistoricalKey implements MulticompanyKey, HistoricalKey {
    private static final long serialVersionUID = 1L;

    /**
     * Service Id
     */
    @Column(name = "SERVICE_ID", nullable = false)
    private String serviceId;

    public ServicePk() {}

    public ServicePk(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "[" + this.getCompanyId() + ", " + this.getExpired() + ", "
                + this.getServiceId() + "]";
    }
}
