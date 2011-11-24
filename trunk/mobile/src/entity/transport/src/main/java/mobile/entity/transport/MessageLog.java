package mobile.entity.transport;


import java.math.BigDecimal;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistoricalLocking;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Historical;
import mobile.entity.schema.OptimisticLocking;


/**
 * The persistent class for the MESSAGE_LOG database table.
 * Values of message logs
 */
@Entity
@Table(name = "MESSAGE_LOG")
public class MessageLog extends AbstractHistoricalLocking implements Multicompany, Historical, OptimisticLocking {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private MessageLogPk pk;

    /**
     * Service Id
     */
    @Column(name = "SERVICE_ID", nullable = false)
    private String serviceId;

    /**
     * Message type Id
     */
    @Column(name = "MESSAGE_TYPE_ID", nullable = false)
    private String messageTypeId;

    /**
     * Host Id
     */
    @Column(name = "HOST_ID", nullable = false)
    private String hostId;

    /**
     * User Id
     */
    @Column(name = "USER_ID", nullable = false)
    private String userId;

    /**
     * Subsystem Id
     */
    @Column(name = "SUBSYSTEM_ID", nullable = false)
    private String subsystemId;

    /**
     * Module Id
     */
    @Column(name = "MODULE_ID", nullable = false)
    private String moduleId;

    /**
     * Process Id
     */
    @Column(name = "PROCESS_ID", nullable = false)
    private String processId;

    /**
     * Processing time
     */
    @Column(name = "PROCESSING_TIME", nullable = true)
    private BigDecimal processingTime;

    /**
     * Message text
     */
    @Column(name = "MESSAGE_TEXT", nullable = true)
    private String messageText;

    /**
     * Response Id
     */
    @Column(name = "RESPONSE_ID", nullable = true)
    private String responseId;

    /**
     * Reverse Id
     */
    @Column(name = "REVERSE_ID", nullable = true)
    private String reverseId;

    public MessageLog() {}

    public MessageLog(MessageLogPk pk) {
        this.pk = pk;
    }

    public MessageLog(MessageLogPk pk, String serviceId, String messageTypeId, String hostId, String userId, String subsystemId, String moduleId, String processId) {
        this.pk = pk;
        this.serviceId = serviceId;
        this.messageTypeId = messageTypeId;
        this.hostId = hostId;
        this.userId = userId;
        this.subsystemId = subsystemId;
        this.moduleId = moduleId;
        this.processId = processId;
    }

    public MessageLogPk getPk() {
        return this.pk;
    }

    public void setPk(MessageLogPk pk) {
        this.pk = pk;
    }

    public String getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getMessageTypeId() {
        return this.messageTypeId;
    }

    public void setMessageTypeId(String messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    public String getHostId() {
        return this.hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubsystemId() {
        return this.subsystemId;
    }

    public void setSubsystemId(String subsystemId) {
        this.subsystemId = subsystemId;
    }

    public String getModuleId() {
        return this.moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getProcessId() {
        return this.processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public BigDecimal getProcessingTime() {
        return this.processingTime;
    }

    public void setProcessingTime(BigDecimal processingTime) {
        this.processingTime = processingTime;
    }

    public String getMessageText() {
        return this.messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getResponseId() {
        return this.responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getReverseId() {
        return this.reverseId;
    }

    public void setReverseId(String reverseId) {
        this.reverseId = reverseId;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (MessageLogPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        MessageLog copy = (MessageLog) super.clone();

        copy.setPk((MessageLogPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "MESSAGE_LOG:[" + this.getPk().toString() + ", "
                + this.getCreated() + ", " + this.getServiceId() + ", "
                + this.getMessageTypeId() + ", " + this.getHostId() + ", "
                + this.getUserId() + ", " + this.getSubsystemId() + ", "
                + this.getModuleId() + ", " + this.getProcessId() + ", "
                + this.getProcessingTime() + ", " + this.getMessageText() + ", "
                + this.getResponseId() + ", " + this.getReverseId() + ", "
                + this.getVersion() + "]";
    }
}
