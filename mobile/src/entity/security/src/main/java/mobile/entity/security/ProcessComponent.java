package mobile.entity.security;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;

/**
 * The persistent class for the PROCESS_COMPONENT database table. Values of
 * process components
 */
@Entity
@Table(name = "PROCESS_COMPONENT")
public class ProcessComponent extends AbstractEntity implements Multicompany {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProcessComponentPk pk;

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

	/**
	 * Enable
	 */
	@Column(name = "ENABLE", nullable = false)
	private Boolean enable;

	/**
	 * Authorize
	 */
	@Column(name = "AUTHORIZE", nullable = true)
	private Boolean authorize;

	public ProcessComponent() {
	}

	public ProcessComponent(ProcessComponentPk pk) {
		this.pk = pk;
	}

	public ProcessComponent(ProcessComponentPk pk, String componentId, String typeId, Boolean enable) {
		this.pk = pk;
		this.componentId = componentId;
		this.typeId = typeId;
		this.enable = enable;
	}

	public ProcessComponentPk getPk() {
		return this.pk;
	}

	public void setPk(ProcessComponentPk pk) {
		this.pk = pk;
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

	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Boolean getAuthorize() {
		return this.authorize;
	}

	public void setAuthorize(Boolean authorize) {
		this.authorize = authorize;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (ProcessComponentPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		ProcessComponent copy = (ProcessComponent) super.clone();
		copy.setPk((ProcessComponentPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "PROCESS_COMPONENT:[" + this.getPk().toString() + ", " + this.getComponentId() + ", " + this.getTypeId()
				+ ", " + this.getEnable() + ", " + this.getAuthorize() + "]";
	}
}
