package mobile.entity.transport;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.GeneralEntity;


/**
 * The persistent class for the SERVER database table.
 * Values of servers
 */
@Entity
@Table(name = "SERVER")
public class Server extends AbstractEntity implements GeneralEntity {
    private static final long serialVersionUID = 1L;

    /**
     * Server Id
     */
    @Id
    @Column(name = "SERVER_ID", nullable = false)
    private String serverId;

    /**
     * Name of server
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    /**
     * URL
     */
    @Column(name = "URL", nullable = false)
    private String url;

    public Server() {}

    public Server(String serverId) {
        this.serverId = serverId;
    }

    public Server(String serverId, String name, String url) {
        this.serverId = serverId;
        this.name = name;
        this.url = url;
    }

    public String getServerId() {
        return this.serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Object getPk() {
        return this.serverId;
    }

    @Override
    public void setPk(Object pk) {
        this.serverId = (String) pk;
    }

    @Override
    public String toString() {
        return "SERVER:[" + this.getPk().toString() + ", " + this.getName()
                + ", " + this.getUrl() + "]";
    }
}
