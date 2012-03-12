package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.GeneralEntity;

/**
 * The persistent class for the COMPONENT_TYPE database table. Values of
 * component types
 */
@Entity
@Table(name = "COMPONENT_TYPE")
public class ComponentType extends AbstractEntity implements GeneralEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * Component type Id
	 */
	@Id
	@Column(name = "COMPONENT_TYPE_ID", nullable = false)
	private String componentTypeId;

	/**
	 * Component type description
	 */
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	public ComponentType() {
	}

	public ComponentType(String componentTypeId) {
		this.componentTypeId = componentTypeId;
	}

	public ComponentType(String componentTypeId, String description) {
		this.componentTypeId = componentTypeId;
		this.description = description;
	}

	public String getComponentTypeId() {
		return this.componentTypeId;
	}

	public void setComponentTypeId(String componentTypeId) {
		this.componentTypeId = componentTypeId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Object getPk() {
		return this.componentTypeId;
	}

	@Override
	public void setPk(Object pk) {
		this.componentTypeId = (String) pk;
	}

	@Override
	public String toString() {
		return "COMPONENT_TYPE:[" + this.getPk().toString() + ", " + this.getDescription() + "]";
	}
}
