package mobile.entity.transport;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.GeneralEntity;


/**
 * The persistent class for the CONTROLLER database table.
 * Values of controllers
 */
@Entity
@Table(name = "CONTROLLER")
public class Controller extends AbstractEntity implements GeneralEntity {
    private static final long serialVersionUID = 1L;

    /**
     * Controller Id
     */
    @Id
    @Column(name = "CONTROLLER_ID", nullable = false)
    private String controllerId;

    /**
     * Name of controller
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * Component Id
     */
    @Column(name = "COMPONENT_ID", nullable = false)
    private String componentId;

    public Controller() {}

    public Controller(String controllerId) {
        this.controllerId = controllerId;
    }

    public Controller(String controllerId, String name, String componentId) {
        this.controllerId = controllerId;
        this.name = name;
        this.componentId = componentId;
    }

    public String getControllerId() {
        return this.controllerId;
    }

    public void setControllerId(String controllerId) {
        this.controllerId = controllerId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponentId() {
        return this.componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    @Override
    public Object getPk() {
        return this.controllerId;
    }

    @Override
    public void setPk(Object pk) {
        this.controllerId = (String) pk;
    }

    @Override
    public String toString() {
        return "CONTROLLER:[" + this.getPk().toString() + ", " + this.getName()
                + ", " + this.getComponentId() + "]";
    }
}
