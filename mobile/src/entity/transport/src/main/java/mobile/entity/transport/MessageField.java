package mobile.entity.transport;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;


/**
 * The persistent class for the MESSAGE_FIELD database table.
 * Values of message fields
 */
@Entity
@Table(name = "MESSAGE_FIELD")
public class MessageField extends AbstractEntity implements Multicompany {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private MessageFieldPk pk;

    /**
     * Field order
     */
    @Column(name = "FIELD_ORDER", nullable = false)
    private Integer fieldOrder;

    /**
     * Field init
     */
    @Column(name = "FIELD_INIT", nullable = true)
    private Integer fieldInit;

    /**
     * Field length
     */
    @Column(name = "FIELD_LENGTH", nullable = true)
    private Integer fieldLength;

    /**
     * Field filled
     */
    @Column(name = "FIELD_FILLED", nullable = true)
    private String fieldFilled;

    /**
     * Field align
     */
    @Column(name = "FIELD_ALIGN", nullable = true)
    private String fieldAlign;

    /**
     * Field value
     */
    @Column(name = "FIELD_VALUE", nullable = true)
    private String fieldValue;

    public MessageField() {}

    public MessageField(MessageFieldPk pk) {
        this.pk = pk;
    }

    public MessageField(MessageFieldPk pk, Integer fieldOrder) {
        this.pk = pk;
        this.fieldOrder = fieldOrder;
    }

    public MessageFieldPk getPk() {
        return this.pk;
    }

    public void setPk(MessageFieldPk pk) {
        this.pk = pk;
    }

    public Integer getFieldOrder() {
        return this.fieldOrder;
    }

    public void setFieldOrder(Integer fieldOrder) {
        this.fieldOrder = fieldOrder;
    }

    public Integer getFieldInit() {
        return this.fieldInit;
    }

    public void setFieldInit(Integer fieldInit) {
        this.fieldInit = fieldInit;
    }

    public Integer getFieldLength() {
        return this.fieldLength;
    }

    public void setFieldLength(Integer fieldLength) {
        this.fieldLength = fieldLength;
    }

    public String getFieldFilled() {
        return this.fieldFilled;
    }

    public void setFieldFilled(String fieldFilled) {
        this.fieldFilled = fieldFilled;
    }

    public String getFieldAlign() {
        return this.fieldAlign;
    }

    public void setFieldAlign(String fieldAlign) {
        this.fieldAlign = fieldAlign;
    }

    public String getFieldValue() {
        return this.fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (MessageFieldPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        MessageField copy = (MessageField) super.clone();

        copy.setPk((MessageFieldPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "MESSAGE_FIELD:[" + this.getPk().toString() + ", "
                + this.getFieldOrder() + ", " + this.getFieldInit() + ", "
                + this.getFieldLength() + ", " + this.getFieldFilled() + ", "
                + this.getFieldAlign() + ", " + this.getFieldValue() + "]";
    }
}
