package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;

/**
 * The primary key class for the FUNDS_DESTINATION database table.
 */
@Embeddable
public class FundsDestinationPk extends AbstractCompanyLanguageKey implements
		MulticompanyKey, MultilanguageKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Destination of funds
	 */
	@Column(name = "FUNDS_DESTINATION_ID", nullable = false)
	private String fundsDestinationId;

	public FundsDestinationPk() {
	}

	public FundsDestinationPk(String fundsDestinationId) {
		this.fundsDestinationId = fundsDestinationId;
	}

	public String getFundsDestinationId() {
		return this.fundsDestinationId;
	}

	public void setFundsDestinationId(String fundsDestinationId) {
		this.fundsDestinationId = fundsDestinationId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", "
				+ this.getFundsDestinationId() + "]";
	}
}
