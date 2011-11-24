package mobile.entity.transport;


import javax.persistence.*;

import mobile.entity.schema.AbstractHistorical;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Historical;


/**
 * The persistent class for the MESSAGE_ROUTER database table.
 * Values of message routers
 */
@Entity
@Table(name = "MESSAGE_ROUTER")
public class MessageRouter extends AbstractHistorical implements Multicompany, Historical {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private MessageRouterPk pk;

    /**
     * Service router
     */
    @Column(name = "SERVICE_ROUTER", nullable = false)
    private String serviceRouter;

    /**
     * Message type router
     */
    @Column(name = "MESSAGE_TYPE_ROUTER", nullable = false)
    private String messageTypeRouter;

    /**
     * Timeout
     */
    @Column(name = "TIMEOUT", nullable = false)
    private Integer timeout;

    public MessageRouter() {}

    public MessageRouter(MessageRouterPk pk) {
        this.pk = pk;
    }

    public MessageRouter(MessageRouterPk pk, String serviceRouter, String messageTypeRouter, Integer timeout) {
        this.pk = pk;
        this.serviceRouter = serviceRouter;
        this.messageTypeRouter = messageTypeRouter;
        this.timeout = timeout;
    }

    public MessageRouterPk getPk() {
        return this.pk;
    }

    public void setPk(MessageRouterPk pk) {
        this.pk = pk;
    }

    public String getServiceRouter() {
        return this.serviceRouter;
    }

    public void setServiceRouter(String serviceRouter) {
        this.serviceRouter = serviceRouter;
    }

    public String getMessageTypeRouter() {
        return this.messageTypeRouter;
    }

    public void setMessageTypeRouter(String messageTypeRouter) {
        this.messageTypeRouter = messageTypeRouter;
    }

    public Integer getTimeout() {
        return this.timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (MessageRouterPk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        MessageRouter copy = (MessageRouter) super.clone();

        copy.setPk((MessageRouterPk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "MESSAGE_ROUTER:[" + this.getPk().toString() + ", "
                + this.getCreated() + ", " + this.getServiceRouter() + ", "
                + this.getMessageTypeRouter() + ", " + this.getTimeout() + "]";
    }
}
