package mobile.entity.parameter;


import javax.persistence.*;

import mobile.entity.schema.AbstractHistorical;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Historical;


/**
 * The persistent class for the PARAMETER database table.
 * Values of parameters
 */
@Entity
@Table(name = "PARAMETER")
public class Parameter extends AbstractHistorical implements Multicompany, Historical {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ParameterPk pk;

    /**
     * Subsystem Id
     */
    @Column(name = "SUBSYSTEM_ID", nullable = false)
    private String subsystemId;

    /**
     * Data type of parameter
     */
    @Column(name = "DATA_TYPE_ID", nullable = false)
    private String dataTypeId;

    /**
     * Value of parameter
     */
    @Column(name = "PARAMETER_VALUE", nullable = false)
    private String parameterValue;

    /**
     * Description of parameter
     */
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    public Parameter() {}

    public Parameter(ParameterPk pk) {
        this.pk = pk;
    }

    public Parameter(ParameterPk pk, String subsystemId, String dataTypeId, String parameterValue, String description) {
        this.pk = pk;
        this.subsystemId = subsystemId;
        this.dataTypeId = dataTypeId;
        this.parameterValue = parameterValue;
        this.description = description;
    }

    public ParameterPk getPk() {
        return this.pk;
    }

    public void setPk(ParameterPk pk) {
        this.pk = pk;
    }

    public String getSubsystemId() {
        return this.subsystemId;
    }

    public void setSubsystemId(String subsystemId) {
        this.subsystemId = subsystemId;
    }

    public String getDataTypeId() {
        return this.dataTypeId;
    }

    public void setDataTypeId(String dataTypeId) {
        this.dataTypeId = dataTypeId;
    }

    public String getParameterValue() {
        return this.parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (ParameterPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Parameter copy = (Parameter) super.clone();

        copy.setPk((ParameterPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "PARAMETER:[" + this.getPk().toString() + ", "
                + this.getCreated() + ", " + this.getSubsystemId() + ", "
                + this.getDataTypeId() + ", " + this.getParameterValue() + ", "
                + this.getDescription() + "]";
    }
}
