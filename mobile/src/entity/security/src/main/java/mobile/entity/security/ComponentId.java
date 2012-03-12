package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the COMPONENT_ID database table.
 */
@Entity
@Table(name = "COMPONENT_ID")
public class ComponentId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ComponentIdPk pk;

	public ComponentId() {
	}

	public ComponentId(ComponentIdPk pk) {
		this.pk = pk;
	}

	public ComponentIdPk getPk() {
		return this.pk;
	}

	public void setPk(ComponentIdPk pk) {
		this.pk = pk;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (ComponentIdPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		ComponentId copy = (ComponentId) super.clone();
		copy.setPk((ComponentIdPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "COMPONENT_ID:[" + this.getPk().toString() + "]";
	}
}
