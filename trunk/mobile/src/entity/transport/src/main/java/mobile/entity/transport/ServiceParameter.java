package mobile.entity.transport;


import javax.persistence.*;

import mobile.entity.schema.AbstractHistorical;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Historical;


/**
 * The persistent class for the SERVICE_PARAMETER database table.
 * Values of services parameters
 */
@Entity
@Table(name = "SERVICE_PARAMETER")
public class ServiceParameter extends AbstractHistorical implements Multicompany, Historical {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ServiceParameterPk pk;

    public ServiceParameter() {}

    public ServiceParameter(ServiceParameterPk pk) {
        this.pk = pk;
    }

    public ServiceParameterPk getPk() {
        return this.pk;
    }

    public void setPk(ServiceParameterPk pk) {
        this.pk = pk;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (ServiceParameterPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ServiceParameter copy = (ServiceParameter) super.clone();

        copy.setPk((ServiceParameterPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "SERVICE_PARAMETER:[" + this.getPk().toString() + ", "
                + this.getCreated() + "]";
    }
}
