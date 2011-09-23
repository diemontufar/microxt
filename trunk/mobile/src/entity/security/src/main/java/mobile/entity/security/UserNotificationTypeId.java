package mobile.entity.security;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;


/**
 * The persistent class for the USER_NOTIFICATION_TYPE_ID database table.
 */
@Entity
@Table(name = "USER_NOTIFICATION_TYPE_ID")
public class UserNotificationTypeId extends AbstractEntityId implements GeneralEntityId {
    private static final long serialVersionUID = 1L;

    /**
     * User notification type Id
     */
    @Id
    @Column(name = "USER_NOTIFICATION_TYPE_ID", nullable = false)
    private String userNotificationTypeId;

    public UserNotificationTypeId() {}

    public UserNotificationTypeId(String userNotificationTypeId) {
        this.userNotificationTypeId = userNotificationTypeId;
    }

    public String getUserNotificationTypeId() {
        return this.userNotificationTypeId;
    }

    public void setUserNotificationTypeId(String userNotificationTypeId) {
        this.userNotificationTypeId = userNotificationTypeId;
    }

    @Override
    public Object getPk() {
        return this.userNotificationTypeId;
    }

    @Override
    public void setPk(Object pk) {
        this.userNotificationTypeId = (String) pk;
    }

    @Override
    public String toString() {
        return "USER_NOTIFICATION_TYPE_ID:[" + this.getPk().toString() + "]";
    }
}
