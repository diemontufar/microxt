package mobile.entity.transport;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;


/**
 * The persistent class for the MESSAGE_MAP database table.
 * Values of message maps
 */
@Entity
@Table(name = "MESSAGE_MAP")
public class MessageMap extends AbstractEntity implements Multicompany {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private MessageMapPk pk;

    /**
     * Operation
     */
    @Column(name = "OPERATION", nullable = false)
    private String operation;

    /**
     * Constant
     */
    @Column(name = "CONSTANT", nullable = true)
    private String constant;

    /**
     * Component Id
     */
    @Column(name = "COMPONENT_ID", nullable = true)
    private String componentId;

    public MessageMap() {}

    public MessageMap(MessageMapPk pk) {
        this.pk = pk;
    }

    public MessageMap(MessageMapPk pk, String operation) {
        this.pk = pk;
        this.operation = operation;
    }

    public MessageMapPk getPk() {
        return this.pk;
    }

    public void setPk(MessageMapPk pk) {
        this.pk = pk;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getConstant() {
        return this.constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    public String getComponentId() {
        return this.componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (MessageMapPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        MessageMap copy = (MessageMap) super.clone();

        copy.setPk((MessageMapPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "MESSAGE_MAP:[" + this.getPk().toString() + ", "
                + this.getOperation() + ", " + this.getConstant() + ", "
                + this.getComponentId() + "]";
    }
}
