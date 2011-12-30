package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the MODULE_ID database table.
 */
@Entity
@Table(name = "MODULE_ID")
public class ModuleId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ModuleIdPk pk;

	public ModuleId() {
	}

	public ModuleId(ModuleIdPk pk) {
		this.pk = pk;
	}

	public ModuleIdPk getPk() {
		return this.pk;
	}

	public void setPk(ModuleIdPk pk) {
		this.pk = pk;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (ModuleIdPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		ModuleId copy = (ModuleId) super.clone();
		copy.setPk((ModuleIdPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "MODULE_ID:[" + this.getPk().toString() + "]";
	}
}
