package mobile.entity.security;


import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;


/**
 * The primary key class for the PROFILE database table.
 */
@Embeddable
public class ProfilePk extends AbstractCompanyLanguageKey implements MulticompanyKey, MultilanguageKey {
    private static final long serialVersionUID = 1L;

    /**
     * Profile Id
     */
    @Column(name = "PROFILE_ID", nullable = false)
    private String profileId;

    public ProfilePk() {}

    public ProfilePk(String profileId) {
        this.profileId = profileId;
    }

    public String getProfileId() {
        return this.profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    @Override
    public String toString() {
        return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", "
                + this.getProfileId() + "]";
    }
}
