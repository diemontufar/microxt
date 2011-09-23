package mobile.entity.security;


import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;


/**
 * The persistent class for the PROFILE database table.
 * Values of profiles
 */
@Entity
@Table(name = "PROFILE")
public class Profile extends AbstractEntity implements Multicompany, Multilanguage {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ProfilePk pk;

    /**
     * Name of profile
     */
    @Column(name = "NAME", nullable = false)
    private String name;

    public Profile() {}

    public Profile(ProfilePk pk) {
        this.pk = pk;
    }

    public Profile(ProfilePk pk, String name) {
        this.pk = pk;
        this.name = name;
    }

    public ProfilePk getPk() {
        return this.pk;
    }

    public void setPk(ProfilePk pk) {
        this.pk = pk;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPk(Object pk) {
        this.pk = (ProfilePk) pk;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Profile copy = (Profile) super.clone();

        copy.setPk((ProfilePk) this.pk.clone());
        return copy;
    }

    @Override
    public String toString() {
        return "PROFILE:[" + this.getPk().toString() + ", " + this.getName()
                + "]";
    }
}
