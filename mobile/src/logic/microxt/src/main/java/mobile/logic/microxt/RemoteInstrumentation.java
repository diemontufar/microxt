package mobile.logic.microxt;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import mobile.common.message.Data;
import mobile.common.message.Field;
import mobile.common.message.Item;
import mobile.common.message.Message;
import mobile.entity.manager.JpManager;
import mobile.entity.microcredit.MicroAccount;
import mobile.entity.microcredit.MicroAccountPk;
import mobile.entity.microcredit.MicroAccountQuota;
import mobile.entity.microcredit.MicroAccountQuotaPk;
import mobile.entity.microcredit.Partner;
import mobile.entity.microcredit.PartnerGroup;
import mobile.entity.microcredit.PartnerGroupPk;
import mobile.entity.microcredit.PartnerPk;
import mobile.entity.microcredit.ProductMicrocredit;
import mobile.entity.microcredit.Solicitude;
import mobile.entity.person.Person;
import mobile.entity.person.PersonPk;
import mobile.tools.common.convertion.FormatDates;
import mobile.tools.common.param.LocalParameter;
import mobile.tools.common.param.ParameterEnum;
import mobile.tools.common.param.Timer;
import mobile.tools.common.structure.MaintenanceProcessor;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

public class RemoteInstrumentation implements MaintenanceProcessor {
	private final String PRODUCT_QL = "Select p from ProductMicrocredit p where p.pk.companyId = :companyId and p.pk.languageId = :languageId and p.pk.expired = :expired";
	private final String SOLICITUDE_QL = "Select s from Solicitude s where s.assessor = :assessor and s.statusId = '002' and s.pk.expired = :expired and s.instrumentationDate is null";
	private int count = 1;

	List<ProductMicrocredit> lProduct = new ArrayList<ProductMicrocredit>();
	List<Solicitude> lSolicitude = new ArrayList<Solicitude>();
	List<MicroAccount> lAccount = new ArrayList<MicroAccount>();
	List<MicroAccountQuota> lAccountQuota = new ArrayList<MicroAccountQuota>();

	@Override
	public Message process(Message msg) throws Exception {
		log.info("Begin remote instrumentation!");

		// Query microcredit products
		getProducts();

		// Query solicitudes for processing
		getSolicitudes(msg);

		// Build message for simulator
		Message msg2 = buildMessageForSimulator();

		// Communicate with simulator
		Message rspMsg = SimulatorClient.sendMessage(msg2);
		if (rspMsg == null) {
			throw new Exception("Invalid response from simulator");
		}

		// Persist response from simulator
		getInstrumentationEntities(rspMsg);

		// Log resulting entities
		log.info("Solicitudes:");
		for (Solicitude solicitude : lSolicitude) {
			log.info(solicitude);
		}
		log.info("Accounts:");
		for (MicroAccount acc : lAccount) {
			log.info(acc);
		}
		log.info("Quotas:");
		for (MicroAccountQuota quota : lAccountQuota) {
			log.info(quota);
		}

		// Persist entities
		for (Solicitude solicitude : lSolicitude) {
			JpManager.update(solicitude);
		}
		for (MicroAccount acc : lAccount) {
			JpManager.persist(acc);
		}
		JpManager.getEntityManager().flush();

		for (MicroAccountQuota quota : lAccountQuota) {
			JpManager.persist(quota);
		}

		return msg;
	}

	private void getProducts() {
		TypedQuery<ProductMicrocredit> query = JpManager.getEntityManager().createQuery(PRODUCT_QL,
				ProductMicrocredit.class);
		query.setHint(QueryHints.READ_ONLY, HintValues.TRUE);
		query.setParameter("companyId", LocalParameter.get(ParameterEnum.COMPANY, String.class));
		query.setParameter("languageId", LocalParameter.get(ParameterEnum.LANGUAGE, String.class));
		query.setParameter("expired", Timer.getExpiredTime());
		List<ProductMicrocredit> results = query.getResultList();

		lProduct.addAll(results);
	}

	private void getSolicitudes(Message msg) {
		String assessor = msg.getControlFieldValue("user");

		TypedQuery<Solicitude> query = JpManager.getEntityManager().createQuery(SOLICITUDE_QL, Solicitude.class);
		// query.setHint(QueryHints.READ_ONLY, HintValues.TRUE);
		query.setParameter("assessor", assessor);
		query.setParameter("expired", Timer.getExpiredTime());
		List<Solicitude> results = query.getResultList();
		lSolicitude.addAll(results);
		JpManager.detachList(lSolicitude);
	}

	private Message buildMessageForSimulator() {
		Message msg2 = new Message();

		// Header
		msg2.getRequest().setProcess("06-2000");
		msg2.getRequest().setProcessType("005");

		// Products
		Data productData = new Data("MICROCREDIT_PRODUCT");
		int i = 1;
		for (ProductMicrocredit product : lProduct) {
			Item item = new Item(i++);
			item.addField(new Field("id", product.getPk().getProductId()));
			item.addField(new Field("rate", product.getRate().toString()));
			productData.addItem(item);
		}
		msg2.addData(productData);

		// Solicitudes
		Data solData = new Data("MICROCREDIT");
		for (Solicitude solicitude : lSolicitude) {
			Item item = solicitudeToItem(solicitude);
			solData.addItem(item);
		}
		msg2.addData(solData);

		return msg2;
	}

	private Item solicitudeToItem(Solicitude sol) {
		Item item = new Item(count++);
		item.addField("id", "" + sol.getPk().getSolicitudeId());
		item.addField("assessor", sol.getAssessor());
		item.addField("individualClientId", "" + sol.getPartnerClientId());
		item.addField("groupalClientId", "" + sol.getGroupClientId());
		item.addField("solicitudeDate", "" + sol.getSolicitudeDate());
		item.addField("productId", "" + sol.getProductId());
		item.addField("numberRenewal", "" + sol.getNumberRenewal());
		item.addField("ammount", "" + sol.getAmount());
		item.addField("term", "" + sol.getTerm());
		item.addField("quotaType", sol.getQuotaTypeId());
		item.addField("frecuency", sol.getPaymentFrequencyId());

		return item;
	}

	private void getInstrumentationEntities(Message msg) throws Exception {
		// Get accounts
		Data accountData = msg.getData("MICROCREDIT_ACCOUNT");

		for (Item item : accountData.getItemList()) {
			Integer solId = Integer.parseInt(item.getFieldValue("solicitudeId"));
			Solicitude solicitude = findSolicitude(solId);

			log.debug("Contains " + JpManager.getEntityManager().contains(solicitude));

			// Update solicitude
			String accId = item.getFieldValue("accountId");
			String statusId = item.getFieldValue("status");
			Integer numberQuotas = Integer.parseInt(item.getFieldValue("quotas"));

			solicitude.setAccount(accId);
			solicitude.setInstrumentationDate(Timer.getCurrentDate());
			solicitude.setNumberQuotas(numberQuotas);

			// Create account
			MicroAccountPk accPk = new MicroAccountPk(accId);
			MicroAccount acc = new MicroAccount(accPk);
			acc.setSolicitudeId(solicitude.getPk().getSolicitudeId());
			acc.setClientName(getClientName(solicitude));
			acc.setAssessor(solicitude.getAssessor());
			acc.setPartnerClientId(solicitude.getPartnerClientId());
			acc.setGroupClientId(solicitude.getGroupClientId());
			acc.setProductId(solicitude.getProductId());
			acc.setStatusId(statusId);
			acc.setNumberRenewal(solicitude.getNumberRenewal());
			acc.setAmount(solicitude.getAmount());
			acc.setTerm(solicitude.getTerm());
			acc.setQuotaTypeId(solicitude.getQuotaTypeId());
			acc.setNumberQuotas(numberQuotas);
			acc.setPaymentFrequencyId(solicitude.getPaymentFrequencyId());

			lAccount.add(acc);
		}

		// Get quotas
		Data quotaData = msg.getData("MICROCREDIT_QUOTA");

		for (Item item : quotaData.getItemList()) {
			// Create quotas
			String accId = item.getFieldValue("accountId");
			Integer id = Integer.parseInt(item.getFieldValue("quotaId"));
			Integer provisionDays = Integer.parseInt(item.getFieldValue("provisionDays"));
			BigDecimal reducedCapital = new BigDecimal(item.getFieldValue("reducedCapital"));
			BigDecimal interest = new BigDecimal(item.getFieldValue("interest"));
			BigDecimal capital = new BigDecimal(item.getFieldValue("capital"));
			//BigDecimal quotaVal = new BigDecimal(item.getFieldValue("quota"));
			Date fromDate = new Date(FormatDates.getDateFormat().parse(item.getFieldValue("fromDate")).getTime());
			Date endDate = new Date(FormatDates.getDateFormat().parse(item.getFieldValue("toDate")).getTime());

			MicroAccountQuotaPk quotaPk = new MicroAccountQuotaPk(accId, id);
			MicroAccountQuota quota = new MicroAccountQuota(quotaPk);
			quota.setProvisionDays(provisionDays);
			quota.setFromDate(fromDate);
			quota.setExpirationDate(endDate);
			quota.setReducedCapital(reducedCapital);
			quota.setCapital(capital);
			quota.setInterest(interest);
			quota.setCharge(BigDecimal.ZERO);
			//quota.setQuota(quotaVal);

			lAccountQuota.add(quota);
		}
	}

	private String getClientName(Solicitude solicitude) throws Exception {
		String clientName = "";
		
		if(solicitude.getPartnerClientId() != null){
			PartnerPk partnerPk = new PartnerPk(solicitude.getPartnerClientId());
			Partner partner = JpManager.find(Partner.class, partnerPk);
			PersonPk personPk = new PersonPk(partner.getPersonId());
			Person person = JpManager.find(Person.class, personPk);
			if(person.getSecondLastName()!=null){
				clientName = person.getLastName() + " " + person.getSecondLastName() + " " + person.getName();
			}else{
				clientName = person.getLastName() + " " + person.getName();
			}
		}else{
			PartnerGroupPk groupPk = new PartnerGroupPk(solicitude.getGroupClientId());
			PartnerGroup group = JpManager.find(PartnerGroup.class, groupPk);
			clientName = group.getGroupDescription();
		}

		return clientName;
	}

	private Solicitude findSolicitude(Integer solicitudeId) {
		for (Solicitude sol : lSolicitude) {
			if (sol.getPk().getSolicitudeId().compareTo(solicitudeId) == 0) {
				return sol;
			}
		}
		return null;
	}
}