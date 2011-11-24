package mobile.entity.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the COMPONENT_ID database table.
 */
@Entity
@Table(name = "COMPONENT_ID")
public class ComponentId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Component Id
	 */
	@Id
	@Column(name = "COMPONENT_ID", nullable = false)
	private String componentId;

	public ComponentId() {
	}

	public ComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getComponentId() {
		return this.componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	@Override
	public Object getPk() {
		return this.componentId;
	}

	@Override
	public void setPk(Object pk) {
		this.componentId = (String) pk;
	}

	@Override
	public String toString() {
		return "COMPONENT_ID:[" + this.getPk().toString() + "]";
	}
}
