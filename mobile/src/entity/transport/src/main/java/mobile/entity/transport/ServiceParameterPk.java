package mobile.entity.transport;


import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.HistoricalKey;


/**
 * The primary key class for the SERVICE_PARAMETER database table.
 */
@Embeddable
public class ServiceParameterPk extends AbstractCompanyHistoricalKey implements MulticompanyKey, HistoricalKey {
    private static final long serialVersionUID = 1L;

    /**
     * Service Id
     */
    @Column(name = "SERVICE_ID", nullable = false)
    private String serviceId;

    /**
     * Parameter Id
     */
    @Column(name = "PARAMETER_ID", nullable = false)
    private String parameterId;

    public ServiceParameterPk() {}

    public ServiceParameterPk(String serviceId, String parameterId) {
        this.serviceId = serviceId;
        this.parameterId = parameterId;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getParameterId() {
        return this.parameterId;
    }

    public void setParameterId(String parameterId) {
        this.parameterId = parameterId;
    }

    @Override
    public String toString() {
        return "[" + this.getCompanyId() + ", " + this.getExpired() + ", "
                + this.getServiceId() + ", " + this.getParameterId() + "]";
    }
}
