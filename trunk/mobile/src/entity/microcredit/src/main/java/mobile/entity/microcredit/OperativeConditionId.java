package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the OPERATIVE_CONDITION_ID database table.
 */
@Entity
@Table(name = "OPERATIVE_CONDITION_ID")
public class OperativeConditionId extends AbstractEntityId implements GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Operative condition
	 */
	@Id
	@Column(name = "OPERATIVE_CONDITION_ID", nullable = false)
	private String operativeConditionId;

	public OperativeConditionId() {
	}

	public OperativeConditionId(String operativeConditionId) {
		this.operativeConditionId = operativeConditionId;
	}

	public String getOperativeConditionId() {
		return this.operativeConditionId;
	}

	public void setOperativeConditionId(String operativeConditionId) {
		this.operativeConditionId = operativeConditionId;
	}

	@Override
	public Object getPk() {
		return this.operativeConditionId;
	}

	@Override
	public void setPk(Object pk) {
		this.operativeConditionId = (String) pk;
	}

	@Override
	public String toString() {
		return "OPERATIVE_CONDITION_ID:[" + this.getPk().toString() + "]";
	}
}
