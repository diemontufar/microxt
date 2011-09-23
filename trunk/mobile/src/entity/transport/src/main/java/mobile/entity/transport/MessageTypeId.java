package mobile.entity.transport;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;


/**
 * The persistent class for the MESSAGE_TYPE_ID database table.
 */
@Entity
@Table(name = "MESSAGE_TYPE_ID")
public class MessageTypeId extends AbstractEntityId implements GeneralEntityId {
    private static final long serialVersionUID = 1L;

    /**
     * Message type Id
     */
    @Id
    @Column(name = "MESSAGE_TYPE_ID", nullable = false)
    private String messageTypeId;

    public MessageTypeId() {}

    public MessageTypeId(String messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public String getMessageTypeId() {
        return this.messageTypeId;
    }

    public void setMessageTypeId(String messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    @Override
    public Object getPk() {
        return this.messageTypeId;
    }

    @Override
    public void setPk(Object pk) {
        this.messageTypeId = (String) pk;
    }

    @Override
    public String toString() {
        return "MESSAGE_TYPE_ID:[" + this.getPk().toString() + "]";
    }
}
