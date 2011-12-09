package mobile.core.processor;

import mobile.core.structure.processor.GeneralProcessor;
import mobile.entity.manager.JPManager;
import mobile.entity.schema.GeneralEntity;
import mobile.message.cmessage.Data;
import mobile.message.cmessage.Item;
import mobile.message.cmessage.Message;
import mobile.tools.common.Log;

import org.apache.log4j.Logger;

public class MaintenanceProcessor implements GeneralProcessor {

	Logger log = Log.getInstance();

	@Override
	public Message process(Message msg) throws Exception {
		for (Data data : msg.getDataList()) {
			if (data.getField("_type") != null
					&& data.getField("_type").getValue().compareTo("MNT") == 0) {
				persistOrUpdateOrDelete(data);
			}
		}

		return msg;
	}

	private void persistOrUpdateOrDelete(Data data) throws Exception {
		for (Item item : data.getItemList()) {
			if (item.getField("_expire") != null
					&& item.getField("_expire").getValue() != null
					&& (item.getField("_expire").getValue().compareTo("1") == 0
					|| item.getField("_expire").getValue().compareTo("true") == 0 )) {
				expireEntity(data.getId(), item);
			}else if (item.getField("_isNew") != null
					&& item.getField("_isNew").getValue() != null
					&& (item.getField("_isNew").getValue().compareTo("1") == 0
					|| item.getField("_isNew").getValue().compareTo("true") == 0 )) {
				persistEntity(data.getId(), item);
			}else{
				updateEntity(data.getId(), item);
			}
		}
	}
	
	private void expireEntity(String entityId, Item item) throws Exception{
		GeneralEntity entity = JPManager.parseEntity(entityId, item, false);
		log.info("Remove " + entity.toString());
		JPManager.delete(entity);
	}

	private void persistEntity(String entityId, Item item) throws Exception{
		GeneralEntity entity = JPManager.parseEntity(entityId, item, true);
		log.info("Persist " + entity.toString());
		JPManager.persist(entity);
	}

	private void updateEntity(String entityId, Item item) throws Exception{
		GeneralEntity entity = JPManager.parseEntity(entityId, item, false);
		log.info("Update " + entity.toString());
		JPManager.update(entity);
	}
}