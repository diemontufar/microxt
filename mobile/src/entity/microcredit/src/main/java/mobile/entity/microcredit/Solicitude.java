package mobile.entity.microcredit;

import java.sql.Date;
import java.sql.Date;
import java.sql.Date;
import java.sql.Date;
import java.math.BigDecimal;

import javax.persistence.*;

import mobile.entity.schema.AbstractHistoricalLocking;
import mobile.entity.schema.Multicompany;
import mobile.entity.schema.Multilanguage;
import mobile.entity.schema.Historical;
import mobile.entity.schema.OptimisticLocking;

/**
 * The persistent class for the SOLICITUDE database table. Solicitude of
 * microcredit
 */
@Entity
@Table(name = "SOLICITUDE")
public class Solicitude extends AbstractHistoricalLocking implements Multicompany, Multilanguage, Historical,
		OptimisticLocking {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SolicitudePk pk;

	/**
	 * Generated account
	 */
	@Column(name = "ACCOUNT", nullable = true)
	private String account;

	/**
	 * Assessor
	 */
	@Column(name = "ASSESSOR", nullable = false)
	private String assessor;

	/**
	 * Client
	 */
	@Column(name = "PARTNER_CLIENT", nullable = true)
	private String partnerClient;

	/**
	 * Group
	 */
	@Column(name = "GROUP_CLIENT", nullable = true)
	private String groupClient;

	/**
	 * Solicitud date
	 */
	@Column(name = "SOLICITUDE_DATE", nullable = false)
	private Date solicitudeDate;

	/**
	 * Approval date
	 */
	@Column(name = "APPROVAL_DATE", nullable = true)
	private Date approvalDate;

	/**
	 * Disbursement date
	 */
	@Column(name = "DISBURSEMENT_DATE", nullable = true)
	private Date disbursementDate;

	/**
	 * Expiration date
	 */
	@Column(name = "EXPIRATION_DATE", nullable = false)
	private Date expirationDate;

	/**
	 * Product id
	 */
	@Column(name = "PRODUCT_ID", nullable = false)
	private String productId;

	/**
	 * Solicitude status id
	 */
	@Column(name = "STATUS_ID", nullable = false)
	private String statusId;

	/**
	 * Number of renewal
	 */
	@Column(name = "NUMBER_RENEWAL", nullable = false)
	private Integer numberRenewal;

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
	 * Fee type id
	 */
	@Column(name = "QUOTA_TYPE_ID", nullable = false)
	private String quotaTypeId;

	/**
	 * Number of fees
	 */
	@Column(name = "NUMBER_FEES", nullable = false)
	private Integer numberFees;

	/**
	 * Frequency of payment
	 */
	@Column(name = "PAYMENT_FREQUENCY_ID", nullable = false)
	private String paymentFrequencyId;

	/**
	 * Destination of funds
	 */
	@Column(name = "FUNDS_DESTINATION_ID", nullable = false)
	private String fundsDestinationId;

	/**
	 * Description of destination
	 */
	@Column(name = "DESTINATION_DESCRIPTION", nullable = false)
	private String destinationDescription;

	public Solicitude() {
	}

	public Solicitude(SolicitudePk pk) {
		this.pk = pk;
	}

	public Solicitude(SolicitudePk pk, String assessor, Date solicitudeDate, Date expirationDate, String productId,
			String statusId, Integer numberRenewal, BigDecimal amount, Long term, String quotaTypeId,
			Integer numberFees, String paymentFrequencyId, String fundsDestinationId, String destinationDescription) {
		this.pk = pk;
		this.assessor = assessor;
		this.solicitudeDate = solicitudeDate;
		this.expirationDate = expirationDate;
		this.productId = productId;
		this.statusId = statusId;
		this.numberRenewal = numberRenewal;
		this.amount = amount;
		this.term = term;
		this.quotaTypeId = quotaTypeId;
		this.numberFees = numberFees;
		this.paymentFrequencyId = paymentFrequencyId;
		this.fundsDestinationId = fundsDestinationId;
		this.destinationDescription = destinationDescription;
	}

	public SolicitudePk getPk() {
		return this.pk;
	}

	public void setPk(SolicitudePk pk) {
		this.pk = pk;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAssessor() {
		return this.assessor;
	}

	public void setAssessor(String assessor) {
		this.assessor = assessor;
	}

	public String getPartnerClient() {
		return this.partnerClient;
	}

	public void setPartnerClient(String partnerClient) {
		this.partnerClient = partnerClient;
	}

	public String getGroupClient() {
		return this.groupClient;
	}

	public void setGroupClient(String groupClient) {
		this.groupClient = groupClient;
	}

	public Date getSolicitudeDate() {
		return this.solicitudeDate;
	}

	public void setSolicitudeDate(Date solicitudeDate) {
		this.solicitudeDate = solicitudeDate;
	}

	public Date getApprovalDate() {
		return this.approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Date getDisbursementDate() {
		return this.disbursementDate;
	}

	public void setDisbursementDate(Date disbursementDate) {
		this.disbursementDate = disbursementDate;
	}

	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
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

	public Integer getNumberRenewal() {
		return this.numberRenewal;
	}

	public void setNumberRenewal(Integer numberRenewal) {
		this.numberRenewal = numberRenewal;
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

	public Integer getNumberFees() {
		return this.numberFees;
	}

	public void setNumberFees(Integer numberFees) {
		this.numberFees = numberFees;
	}

	public String getPaymentFrequencyId() {
		return this.paymentFrequencyId;
	}

	public void setPaymentFrequencyId(String paymentFrequencyId) {
		this.paymentFrequencyId = paymentFrequencyId;
	}

	public String getFundsDestinationId() {
		return this.fundsDestinationId;
	}

	public void setFundsDestinationId(String fundsDestinationId) {
		this.fundsDestinationId = fundsDestinationId;
	}

	public String getDestinationDescription() {
		return this.destinationDescription;
	}

	public void setDestinationDescription(String destinationDescription) {
		this.destinationDescription = destinationDescription;
	}

	@Override
	public void setPk(Object pk) {
		this.pk = (SolicitudePk) pk;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Solicitude copy = (Solicitude) super.clone();
		copy.setPk((SolicitudePk) this.pk.clone());
		return copy;
	}

	@Override
	public String toString() {
		return "SOLICITUDE:[" + this.getPk().toString() + ", " + this.getCreated() + ", " + this.getAccount() + ", "
				+ this.getAssessor() + ", " + this.getPartnerClient() + ", " + this.getGroupClient() + ", "
				+ this.getSolicitudeDate() + ", " + this.getApprovalDate() + ", " + this.getDisbursementDate() + ", "
				+ this.getExpirationDate() + ", " + this.getProductId() + ", " + this.getStatusId() + ", "
				+ this.getNumberRenewal() + ", " + this.getAmount() + ", " + this.getTerm() + ", "
				+ this.getQuotaTypeId() + ", " + this.getNumberFees() + ", " + this.getPaymentFrequencyId() + ", "
				+ this.getFundsDestinationId() + ", " + this.getDestinationDescription() + ", " + this.getVersion()
				+ "]";
	}
}
