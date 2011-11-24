package mobile.entity.security;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import mobile.entity.schema.AbstractCompanyHistoricalKey;
import mobile.entity.schema.HistoricalKey;
import mobile.entity.schema.MulticompanyKey;

/**
 * The primary key class for the USER_PROFILE database table.
 */
@Embeddable
public class UserProfilePk extends AbstractCompanyHistoricalKey implements
		MulticompanyKey, HistoricalKey {
	private static final long serialVersionUID = 1L;

	/**
	 * User Id
	 */
	@Column(name = "USER_ID", nullable = false)
	private String userId;

	/**
	 * Profile Id
	 */
	@Column(name = "PROFILE_ID", nullable = false)
	private String profileId;

	public UserProfilePk() {
	}

	public UserProfilePk(String userId, String profileId) {
		this.userId = userId;
		this.profileId = profileId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProfileId() {
		return this.profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getExpired() + ", "
				+ this.getUserId() + ", " + this.getProfileId() + "]";
	}
}
