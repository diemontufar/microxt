package mobile.entity.common;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the ENTITY_FIELD_ID database table.
 */
@Entity
@Table(name = "ENTITY_FIELD_ID")
public class EntityFieldId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EntityFieldIdPk pk;

	public EntityFieldId() {
	}

	public EntityFieldId(EntityFieldIdPk pk) {
		this.pk = pk;
	}

	public EntityFieldIdPk getPk() {
		return this.pk;
	}

	public void setPk(EntityFieldIdPk pk) {
		this.pk = pk;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (EntityFieldIdPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		EntityFieldId copy = (EntityFieldId) super.clone();

		copy.setPk((EntityFieldIdPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "ENTITY_FIELD_ID:[" + this.getPk().toString() + "]";
	}
}
