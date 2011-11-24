package mobile.entity.transport;


import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyKey;
import mobile.entity.schema.MulticompanyKey;


/**
 * The primary key class for the MESSAGE_FIELD database table.
 */
@Embeddable
public class MessageFieldPk extends AbstractCompanyKey implements MulticompanyKey {
    private static final long serialVersionUID = 1L;

    /**
     * Message type Id
     */
    @Column(name = "MESSAGE_TYPE_ID", nullable = false)
    private String messageTypeId;

    /**
     * Field name
     */
    @Column(name = "FIELD_NAME", nullable = false)
    private String fieldName;

    public MessageFieldPk() {}

    public MessageFieldPk(String messageTypeId, String fieldName) {
        this.messageTypeId = messageTypeId;
        this.fieldName = fieldName;
    }

    public String getMessageTypeId() {
        return this.messageTypeId;
    }

    public void setMessageTypeId(String messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return "[" + this.getCompanyId() + ", " + this.getMessageTypeId() + ", "
                + this.getFieldName() + "]";
    }
}
