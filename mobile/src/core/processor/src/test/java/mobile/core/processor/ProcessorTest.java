package mobile.core.processor;

import java.util.List;

import javax.persistence.Query;

import mobile.entity.manager.JPManager;
import mobile.entity.manager.JPManagerFactory;
import mobile.message.cmessage.Data;
import mobile.message.cmessage.Field;
import mobile.message.cmessage.Item;
import mobile.message.cmessage.Message;
import mobile.tools.common.Log;
import mobile.tools.common.param.LocalParameter;
import mobile.tools.common.param.ParameterEnum;

import org.apache.log4j.Logger;
import org.eclipse.persistence.config.QueryHints;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ProcessorTest{

	Logger log = Log.getInstance();

	@Before
	public void setUp() throws Exception {
		JPManagerFactory.createEntityManagerFactory();
		LocalParameter.set(ParameterEnum.COMPANY, "MXT");
		LocalParameter.set(ParameterEnum.LANGUAGE, "ES");
	}

	@After
	public void tearDown() throws Exception {
		JPManagerFactory.close();
	}

	@Ignore
	@Test
	public void testLoggin() {
		try {
			// Processor
			CoreProcessor proc = new CoreProcessor();

			// Message
			Message msg = new Message();
			Data header = new Data("header");
			header.addField(new Field("proc", "A001"));
			msg.addData(header);

			Data data = new Data("loggin");
			data.addField(new Field("user", "admin"));
			data.addField(new Field("password", "admin"));
			msg.addData(data);

			// Process
			proc.process(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void testJpa() {
		try {
//			String sql = 
//					"Select e.pk.profileId, " +
//					"(Select b.url from Process b where b.pk.subsystemId=e.pk.subsystemId " +
//					"and b.pk.moduleId=e.pk.moduleId and b.pk.processId=e.pk.processId " +
//					"and b.pk.expired = '9999-12-31'  and b.pk.companyId = 'MXT' ), " +
//					"e.editable " +
//					"from Role e where e.pk.expired = :expired and e.pk.companyId = :companyId";

			String sql = 
			"Select e.pk.profileId, e.editable, " +
			"p.url " +
			"from Role e " +
			"inner join Process p on p.pk.companyId=e.pk.companyId and p.pk.expired=e.pk.expired " +
			"and p.pk.subsystemId=e.pk.subsystemId and p.pk.moduleId=e.pk.moduleId " +
			"and p.pk.processId=e.pk.processId";
			
//			String sql = 
//					"Select e.pk.profileId, " + 
//					"e.editable " +
//					"from Role e where e.pk.expired = :expired and e.pk.companyId = :companyId";
			
			JPManager.createEntityManager();
			
			Query query = JPManager.getEntityManager().createQuery(sql);
			//query.setParameter("expired", PersistenceTime.getExpiredTime());
			//query.setParameter("companyId", "MXT");

			query.setHint(QueryHints.READ_ONLY, "1");
			
			List results = query.getResultList();

			// Pagination:
			for (int i = 0; i < results.size(); i++) {
				Object[] result = (Object[]) results.get(i);

				for (Object obj : result) {
					System.out.print(obj + " ");
				}
				System.out.println();
			}

			JPManager.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//@Ignore
	@Test
	public void testQuery1() {
		try {
			JPManager.createEntityManager();
			
			// Processor
			//CoreProcessor proc = new CoreProcessor();
			SpecialQueryProcessor proc = new SpecialQueryProcessor();

			// Message
			Message msg = new Message();
			Data header = new Data("header");
			header.addField(new Field("proc", "A202"));
			msg.addData(header);

			Data data = new Data("Role");
			data.addField(new Field("_type", "QRY"));
			data.addField(new Field("_qry_fields", "pk_profileId;d:Process:url:pk_subsystemId:pk_moduleId:pk_processId;editable"));
			data.addField(new Field("_pag_offset", "0"));
			data.addField(new Field("_pag_limit", "10"));
			//data.addField(new Field("_filters", "pk_parameterId::PARAM11"));
			msg.addData(data);

			// Process
			proc.process(msg);
			
			JPManager.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void testMaintenance1() {
		try {
			// Processor
			CoreProcessor proc = new CoreProcessor();

			// Message
			Message msg = new Message();
			Data header = new Data("header");
			header.addField(new Field("proc", "A002"));
			msg.addData(header);

			Data data = new Data("Parameter");
			data.addField(new Field("_type", "MNT"));
			Item item = new Item(1);
			item.addField(new Field("pk_parameterId", "PARAM66"));
			item.addField(new Field("subsystemId", "44"));
			item.addField(new Field("dataTypeId", "String"));
			item.addField(new Field("parameterValue", "545454"));
			item.addField(new Field("description", "hola mundo222222"));
			//item.addField(new Field("_expire", "1"));
			data.addItem(item);
			msg.addData(data);

			// Process
			proc.process(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Ignore
	@Test
	public void testProcess() {
		try {
			// Processor
			CoreProcessor proc = new CoreProcessor();

			// Message
			Message msg = new Message();
			Data header = new Data("header");
			header.addField(new Field("proc", "G001"));
			msg.addData(header);

			// Process
			proc.process(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
