package mobile.core.processor;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import mobile.common.message.EntityData;
import mobile.common.message.Field;
import mobile.common.message.Item;
import mobile.common.message.Message;
import mobile.entity.manager.JpManager;
import mobile.entity.schema.GeneralEntity;
import mobile.entity.schema.SequentialKey;
import mobile.tools.common.Log;
import mobile.tools.common.convertion.CoreConverter;
import mobile.tools.common.structure.MaintenanceProcessor;

import org.apache.log4j.Logger;

public class GeneralMaintenance implements MaintenanceProcessor {

	Logger log = Log.getInstance();

	private Message msg;

	@Override
	public Message process(Message msg) throws Exception {
		this.msg = msg;
		// Complete items with filters
		for (EntityData data : msg.getEntityDataList()) {
			if (data.getFilters() != null) {
				completeItemsWithFilters(data);
			}
		}

		// Process maintenance
		for (EntityData data : msg.getEntityDataList()) {
			persistOrUpdateOrDelete(data);
		}

		return msg;
	}

	private void persistOrUpdateOrDelete(EntityData data) throws Exception {
		for (Item item : data.getItemList()) {
			if (item.isExpireItem()) {
				expireEntity(data.getDataId(), item);
			} else if (item.isNewItem()) {
				persistEntity(data.getDataId(), item);
			} else {
				updateEntity(data.getDataId(), item);
			}
		}
	}

	private void expireEntity(String entityId, Item item) throws Exception {
		GeneralEntity entity = JpManager.parseEntity(entityId, item, false);
		log.info("Remove " + entity.toString());
		JpManager.delete(entity);
	}

	private void persistEntity(String entityId, Item item) throws Exception {
		GeneralEntity entity = JpManager.parseEntity(entityId, item, true);
		log.info("Persist " + entity.toString());
		JpManager.persist(entity);
		setGeneratedSequence(entity);
	}

	private void updateEntity(String entityId, Item item) throws Exception {
		if(item.getFieldList() != null && item.getFieldList().size()>0){
			GeneralEntity entity = JpManager.parseEntity(entityId, item, false);
			log.info("Update " + entity.toString());
			JpManager.update(entity);
		}
	}

	private String validateNull(String in) {
		return (in == null || in.compareTo("") == 0) ? null : in;
	}

	private void completeItemsWithFilters(EntityData data) throws ParseException {
		// Get completed fields
		List<Field> lfields = new ArrayList<Field>();
		String strFilters = data.getFilters();
		String[] lFilters = strFilters.split(";");
		for (String filter : lFilters) {
			String[] part = filter.split(":");
			String filteredField = part[0];
			// String comparator = validateNull(part[1]);
			String value = null;
			if (part.length > 2) {
				value = validateNull(part[2]);
			}
			if(value != null){
				value = CoreConverter.convertToType(value).toString();
			}
			Field completed = new Field(filteredField, value);
			lfields.add(completed);
		}

		// Set completed fields in items
		for (Item item : data.getItemList()) {
			item.getFieldList().addAll(lfields);
		}
	}

	private void setGeneratedSequence(GeneralEntity entity) {
		if (entity.getPk() instanceof SequentialKey) {
			SequentialKey key = (SequentialKey) entity.getPk();
			msg.setControlFieldValue(Item.GENERATED_ID, key.getId().toString());
		}
	}
}