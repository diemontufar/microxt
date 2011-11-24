package mobile.entity.transport;


import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyKey;
import mobile.entity.schema.MulticompanyKey;


/**
 * The primary key class for the MESSAGE_MAP database table.
 */
@Embeddable
public class MessageMapPk extends AbstractCompanyKey implements MulticompanyKey {
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

    /**
     * Message type map
     */
    @Column(name = "MESSAGE_TYPE_MAP", nullable = false)
    private String messageTypeMap;

    /**
     * Field name map
     */
    @Column(name = "FIELD_NAME_MAP", nullable = false)
    private String fieldNameMap;

    public MessageMapPk() {}

    public MessageMapPk(String messageTypeId, String fieldName, String messageTypeMap, String fieldNameMap) {
        this.messageTypeId = messageTypeId;
        this.fieldName = fieldName;
        this.messageTypeMap = messageTypeMap;
        this.fieldNameMap = fieldNameMap;
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

    public String getMessageTypeMap() {
        return this.messageTypeMap;
    }

    public void setMessageTypeMap(String messageTypeMap) {
        this.messageTypeMap = messageTypeMap;
    }

    public String getFieldNameMap() {
        return this.fieldNameMap;
    }

    public void setFieldNameMap(String fieldNameMap) {
        this.fieldNameMap = fieldNameMap;
    }

    @Override
    public String toString() {
        return "[" + this.getCompanyId() + ", " + this.getMessageTypeId() + ", "
                + this.getFieldName() + ", " + this.getMessageTypeMap() + ", "
                + this.getFieldNameMap() + "]";
    }
}
