package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyKey;
import mobile.entity.schema.MulticompanyKey;

/**
 * The primary key class for the COMPONENT database table.
 */
@Embeddable
public class ComponentPk extends AbstractCompanyKey implements MulticompanyKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Component Id
	 */
	@Column(name = "COMPONENT_ID", nullable = false)
	private String componentId;

	public ComponentPk() {
	}

	public ComponentPk(String componentId) {
		this.componentId = componentId;
	}

	public String getComponentId() {
		return this.componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getComponentId() + "]";
	}
}
