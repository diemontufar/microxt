package mobile.logic.microxt;

import mobile.common.message.EntityData;
import mobile.common.message.Item;
import mobile.common.message.Message;
import mobile.tools.common.Log;
import mobile.tools.common.structure.GeneralProcessor;

import org.apache.log4j.Logger;

public class MicrocreditSolicitud implements GeneralProcessor {

	private final static Logger log = Log.getInstance();

	@Override
	public Message process(Message msg) throws Exception {
		EntityData solicitude = msg.getEntityData("Solicitude");
		Item item = solicitude.getItem(1);

		// Generated id
		String generatedId = item.getFieldValue(Item.GENERATED_ID);
		log.info("Generated id: " + generatedId);

		item.setFieldValue("pk_solicitudeId", generatedId);
		
		return msg;
	}

}
