package mobile.entity.transport;


import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyKey;
import mobile.entity.schema.MulticompanyKey;


/**
 * The primary key class for the MESSAGE_TYPE database table.
 */
@Embeddable
public class MessageTypePk extends AbstractCompanyKey implements MulticompanyKey {
    private static final long serialVersionUID = 1L;

    /**
     * Message type Id
     */
    @Column(name = "MESSAGE_TYPE_ID", nullable = false)
    private String messageTypeId;

    public MessageTypePk() {}

    public MessageTypePk(String messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public String getMessageTypeId() {
        return this.messageTypeId;
    }

    public void setMessageTypeId(String messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    @Override
    public String toString() {
        return "[" + this.getCompanyId() + ", " + this.getMessageTypeId() + "]";
    }
}
