package mobile.entity.transport;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;


/**
 * The persistent class for the MESSAGE_TYPE database table.
 * Values of message types
 */
@Entity
@Table(name = "MESSAGE_TYPE")
public class MessageType extends AbstractEntity implements Multicompany {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private MessageTypePk pk;

    /**
     * Name of message type
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * Component Id
     */
    @Column(name = "COMPONENT_ID", nullable = true)
    private String componentId;

    public MessageType() {}

    public MessageType(MessageTypePk pk) {
        this.pk = pk;
    }

    public MessageType(MessageTypePk pk, String name) {
        this.pk = pk;
        this.name = name;
    }

    public MessageTypePk getPk() {
        return this.pk;
    }

    public void setPk(MessageTypePk pk) {
        this.pk = pk;
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
    public void setPk(Object pk) {
        this.pk = (MessageTypePk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        MessageType copy = (MessageType) super.clone();

        copy.setPk((MessageTypePk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "MESSAGE_TYPE:[" + this.getPk().toString() + ", "
                + this.getName() + ", " + this.getComponentId() + "]";
    }
}
