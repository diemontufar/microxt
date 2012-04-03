package mobile.core.simulator.micro;

import java.math.BigDecimal;
import java.util.Date;

public class Account {
	private String accountId;
	private Integer solicitudId;
	private String status;
	private String product;
	private BigDecimal amount;
	private Integer term;
	private Integer numberQuotas;
	private Date startDate;
	private String quotaType;
	private Integer frequency;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Integer getSolicitudId() {
		return solicitudId;
	}

	public void setSolicitudId(Integer solicitudId) {
		this.solicitudId = solicitudId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public Integer getNumberQuotas() {
		return numberQuotas;
	}

	public void setNumberQuotas(Integer numberQuotas) {
		this.numberQuotas = numberQuotas;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getQuotaType() {
		return quotaType;
	}

	public void setQuotaType(String quotaType) {
		this.quotaType = quotaType;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}
	
}
