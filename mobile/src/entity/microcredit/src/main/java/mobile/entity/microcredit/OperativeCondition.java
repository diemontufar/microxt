package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntity;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;

/**
 * The persistent class for the OPERATIVE_CONDITION database table. Operative
 * conditions
 */
@Entity
@Table(name = "OPERATIVE_CONDITION")
public class OperativeCondition extends AbstractEntity implements Multicompany, Multilanguage {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OperativeConditionPk pk;

	/**
	 * Description
	 */
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	public OperativeCondition() {
	}

	public OperativeCondition(OperativeConditionPk pk) {
		this.pk = pk;
	}

	public OperativeCondition(OperativeConditionPk pk, String description) {
		this.pk = pk;
		this.description = description;
	}

	public OperativeConditionPk getPk() {
		return this.pk;
	}

	public void setPk(OperativeConditionPk pk) {
		this.pk = pk;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (OperativeConditionPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		OperativeCondition copy = (OperativeCondition) super.clone();
		copy.setPk((OperativeConditionPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "OPERATIVE_CONDITION:[" + this.getPk().toString() + ", " + this.getDescription() + "]";
	}
}
