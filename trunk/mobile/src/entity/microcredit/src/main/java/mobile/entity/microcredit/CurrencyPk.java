package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractCompanyLanguageHistoricalKey;
import mobile.entity.schema.MulticompanyKey;
import mobile.entity.schema.MultilanguageKey;
import mobile.entity.schema.HistoricalKey;

/**
 * The primary key class for the CURRENCY database table.
 */
@Embeddable
public class CurrencyPk extends AbstractCompanyLanguageHistoricalKey implements MulticompanyKey, MultilanguageKey,
		HistoricalKey {
	private static final long serialVersionUID = 1L;

	/**
	 * Currency
	 */
	@Column(name = "CURRENCY_ID", nullable = false)
	private String currencyId;

	public CurrencyPk() {
	}

	public CurrencyPk(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	@Override
	public String toString() {
		return "[" + this.getCompanyId() + ", " + this.getLanguageId() + ", " + this.getExpired() + ", "
				+ this.getCurrencyId() + "]";
	}
}
