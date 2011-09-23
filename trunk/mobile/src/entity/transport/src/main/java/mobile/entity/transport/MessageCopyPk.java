package mobile.entity.transport;


import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyKey;
import mobile.entity.schema.MulticompanyKey;


/**
 * The primary key class for the MESSAGE_COPY database table.
 */
@Embeddable
public class MessageCopyPk extends AbstractCompanyKey implements MulticompanyKey {
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
     * Message type copy
     */
    @Column(name = "MESSAGE_TYPE_COPY", nullable = false)
    private String messageTypeCopy;

    /**
     * Field name copy
     */
    @Column(name = "FIELD_NAME_COPY", nullable = false)
    private String fieldNameCopy;

    /**
     * Copy sequence
     */
    @Column(name = "COPY_SEQUENCE", nullable = false)
    private Integer copySequence;

    public MessageCopyPk() {}

    public MessageCopyPk(String messageTypeId, String fieldName, String messageTypeCopy, String fieldNameCopy, Integer copySequence) {
        this.messageTypeId = messageTypeId;
        this.fieldName = fieldName;
        this.messageTypeCopy = messageTypeCopy;
        this.fieldNameCopy = fieldNameCopy;
        this.copySequence = copySequence;
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

    public String getMessageTypeCopy() {
        return this.messageTypeCopy;
    }

    public void setMessageTypeCopy(String messageTypeCopy) {
        this.messageTypeCopy = messageTypeCopy;
    }

    public String getFieldNameCopy() {
        return this.fieldNameCopy;
    }

    public void setFieldNameCopy(String fieldNameCopy) {
        this.fieldNameCopy = fieldNameCopy;
    }

    public Integer getCopySequence() {
        return this.copySequence;
    }

    public void setCopySequence(Integer copySequence) {
        this.copySequence = copySequence;
    }

    @Override
    public String toString() {
        return "[" + this.getCompanyId() + ", " + this.getMessageTypeId() + ", "
                + this.getFieldName() + ", " + this.getMessageTypeCopy() + ", "
                + this.getFieldNameCopy() + ", " + this.getCopySequence() + "]";
    }
}
