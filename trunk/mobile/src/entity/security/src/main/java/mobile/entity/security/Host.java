package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistorical;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Historical;

/**
 * The persistent class for the HOST database table. Values of hosts
 */
@Entity
@Table(name = "HOST")
public class Host extends AbstractHistorical implements Multicompany, Historical {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HostPk pk;

	/**
	 * Address
	 */
	@Column(name = "ADDRESS", nullable = false)
	private String address;

	/**
	 * Time zone
	 */
	@Column(name = "TIME_ZONE", nullable = true)
	private String timeZone;

	public Host() {
	}

	public Host(HostPk pk) {
		this.pk = pk;
	}

	public Host(HostPk pk, String address) {
		this.pk = pk;
		this.address = address;
	}

	public HostPk getPk() {
		return this.pk;
	}

	public void setPk(HostPk pk) {
		this.pk = pk;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTimeZone() {
		return this.timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (HostPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Host copy = (Host) super.clone();
		copy.setPk((HostPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "HOST:[" + this.getPk().toString() + ", " + this.getCreated() + ", " + this.getAddress() + ", "
				+ this.getTimeZone() + "]";
	}
}
