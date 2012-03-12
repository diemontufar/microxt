package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityKey;
import mobile.entity.schema.GeneralEntityKey;

/**
 * The primary key class for the COMPONENT_ID database table.
 */
@Embeddable
public class ComponentIdPk extends AbstractEntityKey implements GeneralEntityKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Component Id
	 */
	@Column(name = "COMPONENT_ID", nullable = false)
	private String componentId;

	/**
	 * Component type Id
	 */
	@Column(name = "TYPE_ID", nullable = false)
	private String typeId;

	public ComponentIdPk() {
	}

	public ComponentIdPk(String componentId, String typeId) {
		this.componentId = componentId;
		this.typeId = typeId;
	}

	public String getComponentId() {
		return this.componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "[" + this.getComponentId() + ", " + this.getTypeId() + "]";
	}
}
