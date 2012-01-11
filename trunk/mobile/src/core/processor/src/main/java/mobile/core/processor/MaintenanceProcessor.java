package mobile.core.processor;

import java.util.ArrayList;
import java.util.List;

import mobile.core.structure.processor.GeneralProcessor;
import mobile.entity.manager.JPManager;
import mobile.entity.schema.GeneralEntity;
import mobile.message.message.Data;
import mobile.message.message.Field;
import mobile.message.message.Item;
import mobile.message.message.Message;
import mobile.tools.common.Log;

import org.apache.log4j.Logger;

public class MaintenanceProcessor implements GeneralProcessor {

	Logger log = Log.getInstance();

	@Override
	public Message process(Message msg) throws Exception {
		// Complete items with filters
		for (Data data : msg.getDataList()) {
			if (data.getField("_type") != null
					&& data.getField("_type").getValue().compareTo("MNT") == 0
					&& data.getField("_filters") != null
					&& data.getField("_filters").getValue()!= null) {
				completeItemsWithFilters(data);
			}
		}
		
		// Process maintenance
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
					&& parseBoolean(item.getField("_expire").getValue())==true) {
				expireEntity(data.getId(), item);
			}else if (item.getField("_isNew") != null
					&& item.getField("_isNew").getValue() != null
					&& parseBoolean(item.getField("_isNew").getValue())==true) {
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
	
	private void completeItemsWithFilters(Data data) {
		// Get completed fields
		List<Field> lfields = new ArrayList<Field>();
		String strFilters = data.getField("_filters").getValue();
		String[] lFilters = strFilters.split(";");
		for (String filter : lFilters) {
			String[] part = filter.split(":");
			Field completed = new Field(part[0], part[2]);
			lfields.add(completed);
		}
		
		// Set completed fields in items
		for (Item item : data.getItemList()) {
			item.getFieldList().addAll(lfields);
		}
	}
	
	private Boolean parseBoolean(String input) {
		Boolean result = false;
		if (input.compareToIgnoreCase("true") == 0 || input.compareTo("1") == 0) {
			result = true;
		}
		return result;
	}

}