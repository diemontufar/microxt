package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.GeneralEntity;

/**
 * The persistent class for the CHANNEL database table. Values of channels
 */
@Entity
@Table(name = "CHANNEL")
public class Channel extends AbstractEntity implements GeneralEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * Channel Id
	 */
	@Id
	@Column(name = "CHANNEL_ID", nullable = false)
	private String channelId;

	/**
	 * Name
	 */
	@Column(name = "NAME", nullable = false)
	private String name;

	public Channel() {
	}

	public Channel(String channelId) {
		this.channelId = channelId;
	}

	public Channel(String channelId, String name) {
		this.channelId = channelId;
		this.name = name;
	}

	public String getChannelId() {
		return this.channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Object getPk() {
		return this.channelId;
	}

	@Override
	public void setPk(Object pk) {
		this.channelId = (String) pk;
	}

	@Override
	public String toString() {
		return "CHANNEL:[" + this.getPk().toString() + ", " + this.getName() + "]";
	}
}
