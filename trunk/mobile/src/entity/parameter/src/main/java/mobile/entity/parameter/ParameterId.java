package mobile.entity.parameter;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the PARAMETER_ID database table.
 */
@Entity
@Table(name = "PARAMETER_ID")
public class ParameterId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Parameter Id
	 */
	@Id
	@Column(name = "PARAMETER_ID", nullable = false)
	private String parameterId;

	public ParameterId() {
	}

	public ParameterId(String parameterId) {
		this.parameterId = parameterId;
	}

	public String getParameterId() {
		return this.parameterId;
	}

	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}

	@Override
	public Object getPk() {
		return this.parameterId;
	}

	@Override
	public void setPk(Object pk) {
		this.parameterId = (String) pk;
	}

	@Override
	public String toString() {
		return "PARAMETER_ID:[" + this.getPk().toString() + "]";
	}
}
