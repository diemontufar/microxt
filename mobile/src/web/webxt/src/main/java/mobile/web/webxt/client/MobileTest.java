package mobile.web.webxt.client;

import mobile.web.webxt.client.data.MyReader;

import org.junit.Test;

public class MobileTest {

	@Test
	public void test() {
//		String data = "{\"message\": {\"data\": [{\"-id\":\"request\",\"process\":\"C301\"},{\"-id\":\"Solicitude\",\"_alias\":\"sol\",\"_type\":\"QRY\",\"_offset_page\":\"0\",\"_qry_fields\":\"statusId;assessorId;numberRenewal;paymentFrequencyId;solicitudeDate;productId;fundsDestinationId;numberQuotas;quotaTypeId;expirationDate;partnerClientId;amount;term\",\"_limit_page\":\"1\",\"_filters\":\"pk_solicitudeId:=:16\"},{\"-id\":\"response\",\"code\":\"001\",\"message\":\"An exception occurred while creating a query in EntityManager: <br/>Exception Description: Error compiling the query [Select a.statusId, a.assessorId, a.numberRenewal, a.paymentFrequencyId, a.solicitudeDate, a.productId, a.fundsDestinationId, a.numberQuotas, a.quotaTypeId, a.expirationDate, a.partnerClientId, a.amount, a.term from Solicitude a where a.pk.solicitudeId= ?1 and a.pk.expired = ?2 and a.pk.companyId = ?3], line 1, column 21: unknown state or association field [assessorId] of class [mobile.entity.microcredit.Solicitude].\"}]}}";
//		MyReader reader = new MyReader();
//		reader.read(null, data);
		
		MyReader reader = new MyReader();
		String value = "((Date))2010-10-10";
		Object cValue = reader.convertToType(value);
		System.out.println(cValue.getClass().getSimpleName() + " " + cValue);
		
		
//		value = "((hello))aasdf";
//		if (value.matches("(\\(\\(hello\\)\\)).*")) {
//			System.out.println("Si");
//		}else{
//			System.out.println("No");
//		}
	}

}
