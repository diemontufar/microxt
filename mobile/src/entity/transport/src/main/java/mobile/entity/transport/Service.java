package mobile.entity.transport;


import javax.persistence.*;

import mobile.entity.schema.AbstractHistoricalLocking;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Historical;
import mobile.entity.schema.OptimisticLocking;


/**
 * The persistent class for the SERVICE database table.
 * Values of services
 */
@Entity
@Table(name = "SERVICE")
public class Service extends AbstractHistoricalLocking implements Multicompany, Historical, OptimisticLocking {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ServicePk pk;

    /**
     * Name of service
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * Server Id
     */
    @Column(name = "SERVER_ID", nullable = false)
    private String serverId;

    /**
     * Channel Id
     */
    @Column(name = "CHANNEL_ID", nullable = false)
    private String channelId;

    /**
     * Controller Id
     */
    @Column(name = "CONTROLLER_ID", nullable = false)
    private String controllerId;

    /**
     * Enable
     */
    @Column(name = "ENABLE", nullable = false)
    private Boolean enable;

    /**
     * Online
     */
    @Column(name = "ONLINE", nullable = false)
    private Boolean online;

    public Service() {}

    public Service(ServicePk pk) {
        this.pk = pk;
    }

    public Service(ServicePk pk, String name, String serverId, String channelId, String controllerId, Boolean enable, Boolean online) {
        this.pk = pk;
        this.name = name;
        this.serverId = serverId;
        this.channelId = channelId;
        this.controllerId = controllerId;
        this.enable = enable;
        this.online = online;
    }

    public ServicePk getPk() {
        return this.pk;
    }

    public void setPk(ServicePk pk) {
        this.pk = pk;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServerId() {
        return this.serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getControllerId() {
        return this.controllerId;
    }

    public void setControllerId(String controllerId) {
        this.controllerId = controllerId;
    }

    public Boolean getEnable() {
        return this.enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getOnline() {
        return this.online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (ServicePk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Service copy = (Service) super.clone();

        copy.setPk((ServicePk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "SERVICE:[" + this.getPk().toString() + ", " + this.getCreated()
                + ", " + this.getName() + ", " + this.getServerId() + ", "
                + this.getChannelId() + ", " + this.getControllerId() + ", "
                + this.getEnable() + ", " + this.getOnline() + ", "
                + this.getVersion() + "]";
    }
}
