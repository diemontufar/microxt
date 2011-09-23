package mobile.entity.security;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;


/**
 * The persistent class for the COMPONENT database table.
 * Values of components
 */
@Entity
@Table(name = "COMPONENT")
public class Component extends AbstractEntity implements Multicompany {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ComponentPk pk;

    /**
     * Subsystem Id
     */
    @Column(name = "SUBSYSTEM_ID", nullable = false)
    private String subsystemId;

    /**
     * Class name
     */
    @Column(name = "CLASS_NAME", nullable = false)
    private String className;

    /**
     * Method name
     */
    @Column(name = "METHOD_NAME", nullable = false)
    private String methodName;

    /**
     * Description
     */
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    public Component() {}

    public Component(ComponentPk pk) {
        this.pk = pk;
    }

    public Component(ComponentPk pk, String subsystemId, String className, String methodName, String description) {
        this.pk = pk;
        this.subsystemId = subsystemId;
        this.className = className;
        this.methodName = methodName;
        this.description = description;
    }

    public ComponentPk getPk() {
        return this.pk;
    }

    public void setPk(ComponentPk pk) {
        this.pk = pk;
    }

    public String getSubsystemId() {
        return this.subsystemId;
    }

    public void setSubsystemId(String subsystemId) {
        this.subsystemId = subsystemId;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (ComponentPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Component copy = (Component) super.clone();

        copy.setPk((ComponentPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "COMPONENT:[" + this.getPk().toString() + ", "
                + this.getSubsystemId() + ", " + this.getClassName() + ", "
                + this.getMethodName() + ", " + this.getDescription() + "]";
    }
}
