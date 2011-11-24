package mobile.entity.transport;


import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.HistoricalKey;


/**
 * The primary key class for the MESSAGE_LOG database table.
 */
@Embeddable
public class MessageLogPk extends AbstractCompanyHistoricalKey implements MulticompanyKey, HistoricalKey {
    private static final long serialVersionUID = 1L;

    /**
     * Message Id
     */
    @Column(name = "MESSAGE_ID", nullable = false)
    private String messageId;

    /**
     * Message In Out
     */
    @Column(name = "MESSAGE_INOUT", nullable = false)
    private String messageInout;

    public MessageLogPk() {}

    public MessageLogPk(String messageId, String messageInout) {
        this.messageId = messageId;
        this.messageInout = messageInout;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageInout() {
        return this.messageInout;
    }

    public void setMessageInout(String messageInout) {
        this.messageInout = messageInout;
    }

    @Override
    public String toString() {
        return "[" + this.getCompanyId() + ", " + this.getExpired() + ", "
                + this.getMessageId() + ", " + this.getMessageInout() + "]";
    }
}
