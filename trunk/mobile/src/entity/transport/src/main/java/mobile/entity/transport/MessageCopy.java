package mobile.entity.transport;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;


/**
 * The persistent class for the MESSAGE_COPY database table.
 * Values of message copies
 */
@Entity
@Table(name = "MESSAGE_COPY")
public class MessageCopy extends AbstractEntity implements Multicompany {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private MessageCopyPk pk;

    /**
     * Field value
     */
    @Column(name = "FIELD_VALUE", nullable = false)
    private String fieldValue;

    /**
     * Field value copy
     */
    @Column(name = "FIELD_VALUE_COPY", nullable = false)
    private String fieldValueCopy;

    public MessageCopy() {}

    public MessageCopy(MessageCopyPk pk) {
        this.pk = pk;
    }

    public MessageCopy(MessageCopyPk pk, String fieldValue, String fieldValueCopy) {
        this.pk = pk;
        this.fieldValue = fieldValue;
        this.fieldValueCopy = fieldValueCopy;
    }

    public MessageCopyPk getPk() {
        return this.pk;
    }

    public void setPk(MessageCopyPk pk) {
        this.pk = pk;
    }

    public String getFieldValue() {
        return this.fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getFieldValueCopy() {
        return this.fieldValueCopy;
    }

    public void setFieldValueCopy(String fieldValueCopy) {
        this.fieldValueCopy = fieldValueCopy;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (MessageCopyPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        MessageCopy copy = (MessageCopy) super.clone();

        copy.setPk((MessageCopyPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "MESSAGE_COPY:[" + this.getPk().toString() + ", "
                + this.getFieldValue() + ", " + this.getFieldValueCopy() + "]";
    }
}
