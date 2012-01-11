package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractEntityId;
import mobile.entity.schema.GeneralEntityId;

/**
 * The persistent class for the FUNDS_DESTINATION_ID database table.
 */
@Entity
@Table(name = "FUNDS_DESTINATION_ID")
public class FundsDestinationId extends AbstractEntityId implements
		GeneralEntityId {
	private static final long serialVersionUID = 1L;

	/**
	 * Destination of funds
	 */
	@Id
	@Column(name = "FUNDS_DESTINATION_ID", nullable = false)
	private String fundsDestinationId;

	public FundsDestinationId() {
	}

	public FundsDestinationId(String fundsDestinationId) {
		this.fundsDestinationId = fundsDestinationId;
	}

	public String getFundsDestinationId() {
		return this.fundsDestinationId;
	}

	public void setFundsDestinationId(String fundsDestinationId) {
		this.fundsDestinationId = fundsDestinationId;
	}

	@Override
	public Object getPk() {
		return this.fundsDestinationId;
	}

	@Override
	public void setPk(Object pk) {
		this.fundsDestinationId = (String) pk;
	}

	@Override
	public String toString() {
		return "FUNDS_DESTINATION_ID:[" + this.getPk().toString() + "]";
	}
}
