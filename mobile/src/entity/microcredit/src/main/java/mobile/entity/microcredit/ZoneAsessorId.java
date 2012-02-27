package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the ZONE_ASESSOR_ID database table.
 */
@Entity
@Table(name = "ZONE_ASESSOR_ID")
public class ZoneAsessorId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * User Id
	 */
	@Id
	@Column(name = "USER_ID", nullable = false)
	private String userId;

	public ZoneAsessorId() {
	}

	public ZoneAsessorId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public Object getPk() {
		return this.userId;
	}

	@Override
	public void setPk(Object pk) {
		this.userId = (String) pk;
	}

	@Override
	public String toString() {
		return "ZONE_ASESSOR_ID:[" + this.getPk().toString() + "]";
	}
}
