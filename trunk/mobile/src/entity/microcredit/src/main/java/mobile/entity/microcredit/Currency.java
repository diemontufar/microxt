package mobile.entity.microcredit;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistorical;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;
import mobile.entity.schema.Historical;

/**
 * The persistent class for the CURRENCY database table. Currency
 */
@Entity
@Table(name = "CURRENCY")
public class Currency extends AbstractHistorical implements Multicompany,
		Multilanguage, Historical {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CurrencyPk pk;

	/**
	 * Description
	 */
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	/**
	 * Initials
	 */
	@Column(name = "INITIALS", nullable = true)
	private String initials;

	public Currency() {
	}

	public Currency(CurrencyPk pk) {
		this.pk = pk;
	}

	public Currency(CurrencyPk pk, String description) {
		this.pk = pk;
		this.description = description;
	}

	public CurrencyPk getPk() {
		return this.pk;
	}

	public void setPk(CurrencyPk pk) {
		this.pk = pk;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInitials() {
		return this.initials;
	}

	public void setInitials(String initials) {
		this.initials = initials;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (CurrencyPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Currency copy = (Currency) super.clone();
		copy.setPk((CurrencyPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "CURRENCY:[" + this.getPk().toString() + ", "
				+ this.getCreated() + ", " + this.getDescription() + ", "
				+ this.getInitials() + "]";
	}
}
