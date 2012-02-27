package mobile.entity.microcredit;

import java.sql.Date;
import java.math.BigDecimal;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistoricalLocking;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Historical;
import mobile.entity.schema.OptimisticLocking;

/**
 * The persistent class for the MICRO_ACCOUNT_QUOTA database table. Microcredit
 * quotas associated to a account
 */
@Entity
@Table(name = "MICRO_ACCOUNT_QUOTA")
public class MicroAccountQuota extends AbstractHistoricalLocking implements Multicompany, Historical, OptimisticLocking {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MicroAccountQuotaPk pk;

	/**
	 * Number of provisioned days
	 */
	@Column(name = "PROVISION_DAYS", nullable = false)
	private Integer provisionDays;

	/**
	 * Initial date for term
	 */
	@Column(name = "FROM_DATE", nullable = false)
	private Date fromDate;

	/**
	 * Expiration date
	 */
	@Column(name = "EXPIRATION_DATE", nullable = false)
	private Date expirationDate;

	/**
	 * Payment date
	 */
	@Column(name = "PAYMENT_DATE", nullable = true)
	private Date paymentDate;

	/**
	 * Capital amount
	 */
	@Column(name = "CAPITAL", nullable = false)
	private BigDecimal capital;

	/**
	 * Interest amount
	 */
	@Column(name = "INTEREST", nullable = false)
	private BigDecimal interest;

	/**
	 * Charge amount
	 */
	@Column(name = "CHARGE", nullable = false)
	private BigDecimal charge;

	public MicroAccountQuota() {
	}

	public MicroAccountQuota(MicroAccountQuotaPk pk) {
		this.pk = pk;
	}

	public MicroAccountQuota(MicroAccountQuotaPk pk, Integer provisionDays, Date fromDate, Date expirationDate,
			BigDecimal capital, BigDecimal interest, BigDecimal charge) {
		this.pk = pk;
		this.provisionDays = provisionDays;
		this.fromDate = fromDate;
		this.expirationDate = expirationDate;
		this.capital = capital;
		this.interest = interest;
		this.charge = charge;
	}

	public MicroAccountQuotaPk getPk() {
		return this.pk;
	}

	public void setPk(MicroAccountQuotaPk pk) {
		this.pk = pk;
	}

	public Integer getProvisionDays() {
		return this.provisionDays;
	}

	public void setProvisionDays(Integer provisionDays) {
		this.provisionDays = provisionDays;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getCapital() {
		return this.capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public BigDecimal getInterest() {
		return this.interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getCharge() {
		return this.charge;
	}

	public void setCharge(BigDecimal charge) {
		this.charge = charge;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (MicroAccountQuotaPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		MicroAccountQuota copy = (MicroAccountQuota) super.clone();
		copy.setPk((MicroAccountQuotaPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "MICRO_ACCOUNT_QUOTA:[" + this.getPk().toString() + ", " + this.getCreated() + ", "
				+ this.getProvisionDays() + ", " + this.getFromDate() + ", " + this.getExpirationDate() + ", "
				+ this.getPaymentDate() + ", " + this.getCapital() + ", " + this.getInterest() + ", "
				+ this.getCharge() + ", " + this.getVersion() + "]";
	}
}
