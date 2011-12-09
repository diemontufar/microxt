package mobile.bus.parameter;

import mobile.core.common.Log;
import mobile.core.structure.processor.GeneralProcessor;
import mobile.message.message.Message;

import org.apache.log4j.Logger;

public class ParameterTest implements GeneralProcessor {

	private final Logger log = Log.getInstance();
	
	@Override
	public Message process(Message msg) throws Exception {
		processQuery(msg);
		processMaintenance(msg);
		return msg;
	}

	private Message processQuery(Message msg) throws Exception {
		try {
			GeneralProcessor queryProc = 
					(GeneralProcessor) Class.forName("mobile.core.processor.QueryProcessor").newInstance();
			queryProc.process(msg);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		
		return msg;
	}
	
	private void processMaintenance(Message msg) {
		try {
			GeneralProcessor maintenanceProc = 
					(GeneralProcessor) Class.forName("mobile.core.processor.MaintenanceProcessor").newInstance();
			maintenanceProc.process(msg);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
	}

}
