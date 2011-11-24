package mobile.entity.security;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the MODULE database table. Values of modules
 */
@Entity
@Table(name = "MODULE")
public class Module extends AbstractEntity implements Multicompany,
		Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ModulePk pk;

	/**
	 * Name of module
	 */
	@Column(name = "NAME", nullable = false)
	private String name;

	public Module() {
	}

	public Module(ModulePk pk) {
		this.pk = pk;
	}

	public Module(ModulePk pk, String name) {
		this.pk = pk;
		this.name = name;
	}

	public ModulePk getPk() {
		return this.pk;
	}

	public void setPk(ModulePk pk) {
		this.pk = pk;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (ModulePk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Module copy = (Module) super.clone();

		copy.setPk((ModulePk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "MODULE:[" + this.getPk().toString() + ", " + this.getName()
				+ "]";
	}
}
