package mobile.entity.microcredit;

import java.math.BigDecimal;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistorical;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;
import mobile.entity.schema.Historical;

/**
 * The persistent class for the PRODUCT_MICROCREDIT database table. Products of
 * microcredit
 */
@Entity
@Table(name = "PRODUCT_MICROCREDIT")
public class ProductMicrocredit extends AbstractHistorical implements Multicompany, Multilanguage, Historical {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductMicrocreditPk pk;

	/**
	 * Description of product
	 */
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	/**
	 * Currency id
	 */
	@Column(name = "CURRENCY_ID", nullable = false)
	private String currencyId;

	/**
	 * Minimun amount
	 */
	@Column(name = "MIN_AMOUNT", nullable = false)
	private BigDecimal minAmount;

	/**
	 * Maximun amount
	 */
	@Column(name = "MAX_AMOUNT", nullable = false)
	private BigDecimal maxAmount;

	/**
	 * Minimun period
	 */
	@Column(name = "MIN_PERIOD", nullable = false)
	private Long minPeriod;

	/**
	 * Maximun period
	 */
	@Column(name = "MAX_PERIOD", nullable = false)
	private Long maxPeriod;

	/**
	 * Rate
	 */
	@Column(name = "RATE", nullable = false)
	private BigDecimal rate;

	public ProductMicrocredit() {
	}

	public ProductMicrocredit(ProductMicrocreditPk pk) {
		this.pk = pk;
	}

	public ProductMicrocredit(ProductMicrocreditPk pk, String description, String currencyId, BigDecimal minAmount,
			BigDecimal maxAmount, Long minPeriod, Long maxPeriod, BigDecimal rate) {
		this.pk = pk;
		this.description = description;
		this.currencyId = currencyId;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
		this.minPeriod = minPeriod;
		this.maxPeriod = maxPeriod;
		this.rate = rate;
	}

	public ProductMicrocreditPk getPk() {
		return this.pk;
	}

	public void setPk(ProductMicrocreditPk pk) {
		this.pk = pk;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public BigDecimal getMinAmount() {
		return this.minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public BigDecimal getMaxAmount() {
		return this.maxAmount;
	}

	public void setMaxAmount(BigDecimal maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Long getMinPeriod() {
		return this.minPeriod;
	}

	public void setMinPeriod(Long minPeriod) {
		this.minPeriod = minPeriod;
	}

	public Long getMaxPeriod() {
		return this.maxPeriod;
	}

	public void setMaxPeriod(Long maxPeriod) {
		this.maxPeriod = maxPeriod;
	}

	public BigDecimal getRate() {
		return this.rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (ProductMicrocreditPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		ProductMicrocredit copy = (ProductMicrocredit) super.clone();
		copy.setPk((ProductMicrocreditPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "PRODUCT_MICROCREDIT:[" + this.getPk().toString() + ", " + this.getCreated() + ", "
				+ this.getDescription() + ", " + this.getCurrencyId() + ", " + this.getMinAmount() + ", "
				+ this.getMaxAmount() + ", " + this.getMinPeriod() + ", " + this.getMaxPeriod() + ", " + this.getRate()
				+ "]";
	}
}
