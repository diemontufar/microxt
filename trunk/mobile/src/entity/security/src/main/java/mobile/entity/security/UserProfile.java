package mobile.entity.security;


import javax.persistence.*;

import mobile.entity.schema.AbstractHistorical;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Historical;


/**
 * The persistent class for the USER_PROFILE database table.
 * Values of user profiles
 */
@Entity
@Table(name = "USER_PROFILE")
public class UserProfile extends AbstractHistorical implements Multicompany, Historical {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private UserProfilePk pk;

    public UserProfile() {}

    public UserProfile(UserProfilePk pk) {
        this.pk = pk;
    }

    public UserProfilePk getPk() {
        return this.pk;
    }

    public void setPk(UserProfilePk pk) {
        this.pk = pk;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (UserProfilePk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        UserProfile copy = (UserProfile) super.clone();

        copy.setPk((UserProfilePk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "USER_PROFILE:[" + this.getPk().toString() + ", "
                + this.getCreated() + "]";
    }
}
