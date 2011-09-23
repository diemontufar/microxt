package mobile.entity.parameter;


import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.HistoricalKey;


/**
 * The primary key class for the PARAMETER database table.
 */
@Embeddable
public class ParameterPk extends AbstractCompanyHistoricalKey implements MulticompanyKey, HistoricalKey {
    private static final long serialVersionUID = 1L;

    /**
     * Parameter Id
     */
    @Column(name = "PARAMETER_ID", nullable = false)
    private String parameterId;

    public ParameterPk() {}

    public ParameterPk(String parameterId) {
        this.parameterId = parameterId;
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
                + this.getParameterId() + "]";
    }
}
