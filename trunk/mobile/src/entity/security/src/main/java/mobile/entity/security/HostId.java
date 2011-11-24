package mobile.entity.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the HOST_ID database table.
 */
@Entity
@Table(name = "HOST_ID")
public class HostId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Host Id
	 */
	@Id
	@Column(name = "HOST_ID", nullable = false)
	private String hostId;

	public HostId() {
	}

	public HostId(String hostId) {
		this.hostId = hostId;
	}

	public String getHostId() {
		return this.hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	@Override
	public Object getPk() {
		return this.hostId;
	}

	@Override
	public void setPk(Object pk) {
		this.hostId = (String) pk;
	}

	@Override
	public String toString() {
		return "HOST_ID:[" + this.getPk().toString() + "]";
	}
}
