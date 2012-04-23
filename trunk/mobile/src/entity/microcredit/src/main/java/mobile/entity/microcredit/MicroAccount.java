package mobile.entity.microcredit;

import java.math.BigDecimal;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistoricalLocking;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Historical;
import mobile.entity.schema.OptimisticLocking;

/**
 * The persistent class for the MICRO_ACCOUNT database table. Microcredit
 * accounts
 */
@Entity
@Table(name = "MICRO_ACCOUNT")
public class MicroAccount extends AbstractHistoricalLocking implements Multicompany, Historical, OptimisticLocking {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MicroAccountPk pk;

	/**
	 * Solicitude Id
	 */
	@Column(name = "SOLICITUDE_ID", nullable = false)
	private Integer solicitudeId;

	/**
	 * Client's name
	 */
	@Column(name = "CLIENT_NAME", nullable = false)
	private String clientName;

	/**
	 * Assessor
	 */
	@Column(name = "ASSESSOR", nullable = false)
	private String assessor;

	/**
	 * Client
	 */
	@Column(name = "PARTNER_CLIENT_ID", nullable = true)
	private Integer partnerClientId;

	/**
	 * Group
	 */
	@Column(name = "GROUP_CLIENT_ID", nullable = true)
	private Integer groupClientId;

	/**
	 * Product id
	 */
	@Column(name = "PRODUCT_ID", nullable = false)
	private String productId;

	/**
	 * Account status id
	 */
	@Column(name = "STATUS_ID", nullable = false)
	private String statusId;

	/**
	 * Renewed account
	 */
	@Column(name = "RENEWED_ACCOUNT", nullable = true)
	private String renewedAccount;

	/**
	 * Number of renewal
	 */
	@Column(name = "NUMBER_RENEWAL", nullable = false)
	private Integer numberRenewal;

	/**
	 * Renewed account
	 */
	@Column(name = "PREVIOUS_ACCOUNT", nullable = true)
	private String previousAccount;

	/**
	 * Amount
	 */
	@Column(name = "AMOUNT", nullable = false)
	private BigDecimal amount;

	/**
	 * Term
	 */
	@Column(name = "TERM", nullable = false)
	private Long term;

	/**
	 * Quota type id
	 */
	@Column(name = "QUOTA_TYPE_ID", nullable = false)
	private String quotaTypeId;

	/**
	 * Number of fees
	 */
	@Column(name = "NUMBER_QUOTAS", nullable = false)
	private Integer numberQuotas;

	/**
	 * Frequency of payment
	 */
	@Column(name = "PAYMENT_FREQUENCY_ID", nullable = false)
	private String paymentFrequencyId;

	public MicroAccount() {
	}

	public MicroAccount(MicroAccountPk pk) {
		this.pk = pk;
	}

	public MicroAccount(MicroAccountPk pk, Integer solicitudeId, String clientName, String assessor, String productId,
			String statusId, Integer numberRenewal, BigDecimal amount, Long term, String quotaTypeId,
			Integer numberQuotas, String paymentFrequencyId) {
		this.pk = pk;
		this.solicitudeId = solicitudeId;
		this.clientName = clientName;
		this.assessor = assessor;
		this.productId = productId;
		this.statusId = statusId;
		this.numberRenewal = numberRenewal;
		this.amount = amount;
		this.term = term;
		this.quotaTypeId = quotaTypeId;
		this.numberQuotas = numberQuotas;
		this.paymentFrequencyId = paymentFrequencyId;
	}

	public MicroAccountPk getPk() {
		return this.pk;
	}

	public void setPk(MicroAccountPk pk) {
		this.pk = pk;
	}

	public Integer getSolicitudeId() {
		return this.solicitudeId;
	}

	public void setSolicitudeId(Integer solicitudeId) {
		this.solicitudeId = solicitudeId;
	}

	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getAssessor() {
		return this.assessor;
	}

	public void setAssessor(String assessor) {
		this.assessor = assessor;
	}

	public Integer getPartnerClientId() {
		return this.partnerClientId;
	}

	public void setPartnerClientId(Integer partnerClientId) {
		this.partnerClientId = partnerClientId;
	}

	public Integer getGroupClientId() {
		return this.groupClientId;
	}

	public void setGroupClientId(Integer groupClientId) {
		this.groupClientId = groupClientId;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getStatusId() {
		return this.statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getRenewedAccount() {
		return this.renewedAccount;
	}

	public void setRenewedAccount(String renewedAccount) {
		this.renewedAccount = renewedAccount;
	}

	public Integer getNumberRenewal() {
		return this.numberRenewal;
	}

	public void setNumberRenewal(Integer numberRenewal) {
		this.numberRenewal = numberRenewal;
	}

	public String getPreviousAccount() {
		return this.previousAccount;
	}

	public void setPreviousAccount(String previousAccount) {
		this.previousAccount = previousAccount;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getTerm() {
		return this.term;
	}

	public void setTerm(Long term) {
		this.term = term;
	}

	public String getQuotaTypeId() {
		return this.quotaTypeId;
	}

	public void setQuotaTypeId(String quotaTypeId) {
		this.quotaTypeId = quotaTypeId;
	}

	public Integer getNumberQuotas() {
		return this.numberQuotas;
	}

	public void setNumberQuotas(Integer numberQuotas) {
		this.numberQuotas = numberQuotas;
	}

	public String getPaymentFrequencyId() {
		return this.paymentFrequencyId;
	}

	public void setPaymentFrequencyId(String paymentFrequencyId) {
		this.paymentFrequencyId = paymentFrequencyId;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (MicroAccountPk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		MicroAccount copy = (MicroAccount) super.clone();
		copy.setPk((MicroAccountPk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "MICRO_ACCOUNT:[" + this.getPk().toString() + ", " + this.getCreated() + ", " + this.getSolicitudeId()
				+ ", " + this.getClientName() + ", " + this.getAssessor() + ", " + this.getPartnerClientId() + ", "
				+ this.getGroupClientId() + ", " + this.getProductId() + ", " + this.getStatusId() + ", "
				+ this.getRenewedAccount() + ", " + this.getNumberRenewal() + ", " + this.getPreviousAccount() + ", "
				+ this.getAmount() + ", " + this.getTerm() + ", " + this.getQuotaTypeId() + ", "
				+ this.getNumberQuotas() + ", " + this.getPaymentFrequencyId() + ", " + this.getVersion() + "]";
	}
}
