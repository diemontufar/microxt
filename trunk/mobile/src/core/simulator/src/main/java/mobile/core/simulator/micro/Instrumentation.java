package mobile.core.simulator.micro;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobile.common.message.Data;
import mobile.common.message.Item;
import mobile.common.message.Message;
import mobile.tools.common.Log;
import mobile.tools.common.convertion.FormatDates;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;

public class Instrumentation {

	private final Integer WEEK_DAYS = 7;
	private final Integer FORTNIGHT_DAYS = 15;
	private final Integer MONTH_DAYS = 30;
	private final Integer PAYMENT_BASE = 360; // 365/360

	private final Logger log = Log.getInstance();

	private Map<String, BigDecimal> mRates = new HashMap<String, BigDecimal>();
	private List<Account> lAccount = new ArrayList<Account>();
	private List<AccountQuota> lAccountQuota = new ArrayList<AccountQuota>();

	public Message process(Message msg) throws Exception {
		String tx = msg.getRequest().getProcess();
		if (tx.compareTo("06-2000") != 0) {
			return msg;
		}
		String type = msg.getRequest().getProcessType();
		log.info("Transaction: " + tx + " (" + type + ")");

		// Register rates
		fillRates(msg);

		// Process instrumentation
		processInstrumentation(msg);

		// Fill accounts and quotas
		fillMessage(msg);

		return msg;
	}

	private void fillRates(Message msg) {
		Data productData = msg.getData("MICROCREDIT_PRODUCT");

		for (Item item : productData.getItemList()) {
			// Generate account
			String id = item.getField("id").getValue();
			BigDecimal rate = new BigDecimal(item.getField("rate").getValue());
			rate = rate.divide(new BigDecimal(100.0), 6, RoundingMode.HALF_UP);

			log.info("Register rate> " + id + " : " + rate);
			mRates.put(id, rate);
		}
	}

	private void processInstrumentation(Message msg) throws Exception {
		Data microcreditData = msg.getData("MICROCREDIT");

		for (Item item : microcreditData.getItemList()) {
			// Generate account
			Account acc = createAccount(item);
			lAccount.add(acc);

			// Generate quotas
			List<AccountQuota> lac = createQuotas(acc);
			lAccountQuota.addAll(lac);
		}
	}

	private Account createAccount(Item item) throws Exception {
		Account acc = new Account();

		// Read data
		Integer solId = Integer.parseInt(item.getField("id").getValue());
		BigDecimal amount = new BigDecimal(item.getField("ammount").getValue());
		Integer term = Integer.parseInt(item.getField("term").getValue());
		Date startDate = FormatDates.getDateFormat().parse(item.getField("solicitudeDate").getValue());
		String product = item.getField("productId").getValue();
		String quotaType = item.getField("quotaType").getValue();
		String frequency = item.getField("frecuency").getValue();

		acc.setAccountId(solId.toString());
		acc.setSolicitudId(solId);
		acc.setStatus("002");
		acc.setProduct(product);
		acc.setAmount(amount);
		acc.setTerm(term);
		acc.setStartDate(startDate);
		acc.setQuotaType(quotaType);
		acc.setFrequency(Integer.parseInt(frequency));

		return acc;
	}

	private List<AccountQuota> createQuotas(Account acc) {
		List<AccountQuota> lAccQuota = new ArrayList<AccountQuota>();

		lAccQuota = calculateQuotas(acc);

		return lAccQuota;
	}

	private List<AccountQuota> calculateQuotas(Account acc) {
		// Read data from account
		BigDecimal capital = acc.getAmount();
		String product = acc.getProduct();
		BigDecimal rate = mRates.get(product);
		Integer term = acc.getTerm();
		Date startDate = acc.getStartDate();
		Integer freq = acc.getFrequency();

		if (acc.getQuotaType().compareTo("AMR") != 0) {
			throw new RuntimeException("AMORTIZACIÓN NO SOPORTADA");
		}

		log.info("Begin instrumentation::::::::::::::::::::::::::::::::::::::");
		log.info("Capital: " + capital);
		log.info("Product: " + product);
		log.info("Rate: " + rate); // annual rate
		log.info("Term: " + term);
		log.info("Start date: " + FormatDates.getDateFormat().format(startDate));
		log.info("Frequency: " + freq);

		// Process
		BigDecimal dailyRate = rate.divide(BigDecimal.valueOf(PAYMENT_BASE), 6, RoundingMode.HALF_UP);
		Integer provisionDays = null;
		BigDecimal calcRate = null;
		Integer calcTerm = null;
		if (freq == 0) { // at maturity
			provisionDays = term;
		} else if (freq == 1) { // daily
			provisionDays = 1;
		} else if (freq == 2) { // weekly
			provisionDays = WEEK_DAYS;
		} else if (freq == 3) { // biweekly
			provisionDays = FORTNIGHT_DAYS;
		} else if (freq == 4) { // monthly
			provisionDays = MONTH_DAYS;
		} else if (freq == 5) { // bimonthly
			provisionDays = 2 * MONTH_DAYS;
		} else if (freq == 6) { // quarterly
			provisionDays = 3 * MONTH_DAYS;
		} else if (freq == 7) { // semiannual
			provisionDays = 6 * MONTH_DAYS;
		} else if (freq == 8) { // annual
			provisionDays = 12 * MONTH_DAYS;
		}

		calcRate = BigDecimal.valueOf(provisionDays).multiply(dailyRate);
		calcTerm = term / provisionDays;

		log.info("Daily rate: " + dailyRate);
		log.info("Calculus rate: " + calcRate);
		log.info("Calculos periods: " + calcTerm);

		acc.setNumberQuotas(calcTerm);

		BigDecimal fixedQuota = pmt(calcRate, calcTerm, capital);
		log.info("Fixed quota: " + fixedQuota);

		List<AccountQuota> lquota = new ArrayList<AccountQuota>();

		Date next = startDate;
		for (int i = 0; i < calcTerm; i++) {
			AccountQuota q = new AccountQuota();
			q.setAccount(acc);
			q.setId(i + 1);
			q.setProvisionDays(provisionDays);
			if (i == 0) {
				q.setReducedCapital(capital);
			} else {
				AccountQuota aq = lquota.get(i - 1);
				BigDecimal reduced = aq.getReducedCapital().subtract(aq.getCapital());
				q.setReducedCapital(reduced);
			}
			BigDecimal inter0 = q.getReducedCapital().multiply(dailyRate).multiply(BigDecimal.valueOf(provisionDays));
			q.setInterest(inter0);
			q.setCapital(fixedQuota.subtract(inter0));
			q.setQuota(fixedQuota);
			q.setProvisionDays(provisionDays);

			q.setFromDate(next);
			next = DateUtils.addDays(next, provisionDays);
			q.setToDate(next);

			lquota.add(q);
		}

		for (AccountQuota quota : lquota) {
			log.info(quota);
		}

		return lquota;
	}

	public BigDecimal pmt(BigDecimal rate, Integer nper, BigDecimal pv) {
		// rate: the rate of interest per period
		// nper: payment period, the total number of periods in which the
		// annuity is paid
		// pv: present value. The current value of a series of payments

		// A = P i(1+i)^n
		// (1+i)^n -1

		BigDecimal i = rate;
		Integer n = nper;
		BigDecimal p = pv;

		BigDecimal a00 = i.add(BigDecimal.ONE).pow(n);
		BigDecimal a0 = i.multiply(a00);
		BigDecimal a1 = a00.subtract(BigDecimal.ONE);

		BigDecimal pmt = p.multiply(a0.divide(a1, 6, RoundingMode.HALF_UP));

		return pmt;
	}

	private void fillMessage(Message msg) {
		// Fill accounts information
		log.info("Filling accounts... " + lAccount.size());
		Data accountData = new Data("MICROCREDIT_ACCOUNT");

		int i = 1;
		for (Account acc : lAccount) {
			Item item = new Item(i++);
			item.setFieldValue("accountId", acc.getAccountId());
			item.setFieldValue("solicitudeId", acc.getSolicitudId().toString());
			item.setFieldValue("status", acc.getStatus());
			item.setFieldValue("product", acc.getProduct());
			item.setFieldValue("amount", acc.getAmount().toString());
			item.setFieldValue("term", acc.getTerm().toString());
			item.setFieldValue("quotas", acc.getNumberQuotas().toString());
			item.setFieldValue("startDate", FormatDates.getDateFormat().format(acc.getStartDate()));
			accountData.addItem(item);
		}
		msg.addData(accountData);

		// Fill quotas
		log.info("Filling quotas... " + lAccountQuota.size());
		Data quotaData = new Data("MICROCREDIT_QUOTA");

		i = 1;
		for (AccountQuota acc : lAccountQuota) {
			Item item = new Item(i++);
			item.setFieldValue("accountId", acc.getAccount().getAccountId());
			item.setFieldValue("quotaId", acc.getId().toString());
			item.setFieldValue("provisionDays", acc.getProvisionDays().toString());
			item.setFieldValue("reducedCapital", convertDecimal(acc.getReducedCapital()));
			item.setFieldValue("interest", convertDecimal(acc.getInterest()));
			item.setFieldValue("capital", convertDecimal(acc.getCapital()));
			item.setFieldValue("quota", convertDecimal(acc.getQuota()));
			item.setFieldValue("fromDate", FormatDates.getDateFormat().format(acc.getFromDate()));
			item.setFieldValue("toDate", FormatDates.getDateFormat().format(acc.getToDate()));
			quotaData.addItem(item);
		}
		msg.addData(quotaData);
	}

	private String convertDecimal(BigDecimal decimal) {
		BigDecimal aux = decimal.setScale(2, RoundingMode.HALF_UP);
		return aux.toString();
	}

	public static void main(String[] args) throws Exception {
		Account acc = new Account();
		acc.setAccountId("1000");
		acc.setAmount(new BigDecimal("100"));
		acc.setProduct("M01");
		acc.setTerm(360);
		acc.setStartDate(FormatDates.getDateFormat().parse("2012-01-01"));
		acc.setQuotaType("AMR");
		acc.setFrequency(4);

		Instrumentation proc = new Instrumentation();

		proc.mRates.put("M01", new BigDecimal(0.1));
		proc.calculateQuotas(acc);
	}

}