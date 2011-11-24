package mobile.entity.transport;


import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.HistoricalKey;


/**
 * The primary key class for the MESSAGE_ROUTER database table.
 */
@Embeddable
public class MessageRouterPk extends AbstractCompanyHistoricalKey implements MulticompanyKey, HistoricalKey {
    private static final long serialVersionUID = 1L;

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
     * Router sequence
     */
    @Column(name = "ROUTER_SEQUENCE", nullable = false)
    private Integer routerSequence;

    public MessageRouterPk() {}

    public MessageRouterPk(String serviceId, String messageTypeId, String subsystemId, String moduleId, String processId, Integer routerSequence) {
        this.serviceId = serviceId;
        this.messageTypeId = messageTypeId;
        this.subsystemId = subsystemId;
        this.moduleId = moduleId;
        this.processId = processId;
        this.routerSequence = routerSequence;
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

    public Integer getRouterSequence() {
        return this.routerSequence;
    }

    public void setRouterSequence(Integer routerSequence) {
        this.routerSequence = routerSequence;
    }

    @Override
    public String toString() {
        return "[" + this.getCompanyId() + ", " + this.getExpired() + ", "
                + this.getServiceId() + ", " + this.getMessageTypeId() + ", "
                + this.getSubsystemId() + ", " + this.getModuleId() + ", "
                + this.getProcessId() + ", " + this.getRouterSequence() + "]";
    }
}
