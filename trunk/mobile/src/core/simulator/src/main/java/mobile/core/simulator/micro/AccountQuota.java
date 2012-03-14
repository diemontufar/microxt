package mobile.core.simulator.micro;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import mobile.tools.common.convertion.FormatDates;

public class AccountQuota {
	private Account account;
	private Integer id;
	private Integer provisionDays;
	private BigDecimal reducedCapital;
	private BigDecimal interest;
	private BigDecimal capital;
	private BigDecimal quota;
	private Date fromDate;
	private Date toDate;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProvisionDays() {
		return provisionDays;
	}

	public void setProvisionDays(Integer provisionDays) {
		this.provisionDays = provisionDays;
	}

	public BigDecimal getReducedCapital() {
		return reducedCapital;
	}

	public void setReducedCapital(BigDecimal reducedCapital) {
		this.reducedCapital = reducedCapital;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interes) {
		this.interest = interes;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public BigDecimal getQuota() {
		return quota;
	}

	public void setQuota(BigDecimal quota) {
		this.quota = quota;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date from) {
		this.fromDate = from;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date to) {
		this.toDate = to;
	}

	@Override
	public String toString() {
		SimpleDateFormat format = FormatDates.getDateFormat();
		return String.format(
				"No: %2d, reducedCapital: %6.2f, interest: %6.2f, capital: %6.2f, quota: %6.2f, from: %s, to: %s", id,
				reducedCapital, interest, capital, quota, format.format(fromDate), format.format(toDate));
	}
}
