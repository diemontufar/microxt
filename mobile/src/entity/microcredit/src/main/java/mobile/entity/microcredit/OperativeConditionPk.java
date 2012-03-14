package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the OPERATIVE_CONDITION database table.
 */
@Embeddable
public class OperativeConditionPk extends AbstractCompanyLanguageKey implements MulticompanyKey, MultilanguageKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Operative condition
	 */
	@Column(name = "OPERATIVE_CONDITION_ID", nullable = false)
	private String operativeConditionId;

	public OperativeConditionPk() {
	}

	public OperativeConditionPk(String operativeConditionId) {
		this.operativeConditionId = operativeConditionId;
	}

	public String getOperativeConditionId() {
		return this.operativeConditionId;
	}

	public void setOperativeConditionId(String operativeConditionId) {
		this.operativeConditionId = operativeConditionId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getOperativeConditionId() + "]";
	}
}
