package mobile.core.processor;

import mobile.core.common.Log;
import mobile.core.message.Data;
import mobile.core.message.Field;
import mobile.core.message.Item;
import mobile.core.message.Message;
import mobile.entity.manager.JPManagerFactory;
import mobile.entity.manager.util.LocalParameter;
import mobile.entity.manager.util.ParameterEnum;

import org.apache.log4j.Logger;
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

	//@Ignore
	@Test
	public void testQuery1() {
		try {
			// Processor
			CoreProcessor proc = new CoreProcessor();

			// Message
			Message msg = new Message();
			Data header = new Data("header");
			header.addField(new Field("proc", "A002"));
			msg.addData(header);

			Data data = new Data("Parameter");
			data.addField(new Field("_type", "QRY"));
			data.addField(new Field("_qry_fields", "pk_parameterId;subsystemId;dataTypeId;parameterValue;description"));
			data.addField(new Field("_pag_offset", "0"));
			data.addField(new Field("_pag_limit", "10"));
			data.addField(new Field("_filters", "pk_parameterId::PARAM11"));
			
			msg.addData(data);

			// Process
			proc.process(msg);
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
