package mobile.entity.security;


import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.HistoricalKey;


/**
 * The primary key class for the USER_SESSION database table.
 */
@Embeddable
public class UserSessionPk extends AbstractCompanyHistoricalKey implements MulticompanyKey, HistoricalKey {
    private static final long serialVersionUID = 1L;

    /**
     * User Id
     */
    @Column(name = "USER_ID", nullable = false)
    private String userId;

    /**
     * Host Id
     */
    @Column(name = "HOST_ID", nullable = false)
    private String hostId;

    public UserSessionPk() {}

    public UserSessionPk(String userId, String hostId) {
        this.userId = userId;
        this.hostId = hostId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHostId() {
        return this.hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    @Override
    public String toString() {
        return "[" + this.getCompanyId() + ", " + this.getExpired() + ", "
                + this.getUserId() + ", " + this.getHostId() + "]";
    }
}
