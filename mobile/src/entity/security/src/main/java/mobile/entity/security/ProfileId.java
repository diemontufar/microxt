package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the PROFILE_ID database table.
 */
@Entity
@Table(name = "PROFILE_ID")
public class ProfileId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Profile Id
	 */
	@Id
	@Column(name = "PROFILE_ID", nullable = false)
	private String profileId;

	public ProfileId() {
	}

	public ProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getProfileId() {
		return this.profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	@Override
	public Object getPk() {
		return this.profileId;
	}

	@Override
	public void setPk(Object pk) {
		this.profileId = (String) pk;
	}

	@Override
	public String toString() {
		return "PROFILE_ID:[" + this.getPk().toString() + "]";
	}
}
