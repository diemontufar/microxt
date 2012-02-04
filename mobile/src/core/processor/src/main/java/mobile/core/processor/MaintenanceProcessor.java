package mobile.core.processor;

import java.util.ArrayList;
import java.util.List;

import mobile.common.message.EntityData;
import mobile.common.message.Field;
import mobile.common.message.Item;
import mobile.common.message.Message;
import mobile.common.tools.ProcessType;
import mobile.entity.manager.JPManager;
import mobile.entity.schema.GeneralEntity;
import mobile.entity.schema.SequentialKey;
import mobile.tools.common.Log;
import mobile.tools.common.structure.GeneralProcessor;

import org.apache.log4j.Logger;

public class MaintenanceProcessor implements GeneralProcessor {

	Logger log = Log.getInstance();

	private Message msg;

	@Override
	public Message process(Message msg) throws Exception {
		this.msg = msg;
		// Complete items with filters
		for (EntityData data : msg.getEntityDataList()) {
			if (data.getProcessType() != null
					&& data.getProcessType().compareTo(ProcessType.MAINTENANCE.getShortName()) == 0
					&& data.getFilters() != null) {
				completeItemsWithFilters(data);
			}
		}

		// Process maintenance
		for (EntityData data : msg.getEntityDataList()) {
			if (data.getProcessType() != null
					&& data.getProcessType().compareTo(ProcessType.MAINTENANCE.getShortName()) == 0) {
				persistOrUpdateOrDelete(data);
			}
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
		GeneralEntity entity = JPManager.parseEntity(entityId, item, false);
		log.info("Remove " + entity.toString());
		JPManager.delete(entity);
	}

	private void persistEntity(String entityId, Item item) throws Exception {
		GeneralEntity entity = JPManager.parseEntity(entityId, item, true);
		log.info("Persist " + entity.toString());
		JPManager.persist(entity);
		setGeneratedSequence(entity);
	}

	private void updateEntity(String entityId, Item item) throws Exception {
		GeneralEntity entity = JPManager.parseEntity(entityId, item, false);
		log.info("Update " + entity.toString());
		JPManager.update(entity);
	}

	private String validateNull(String in) {
		return (in == null || in.compareTo("") == 0) ? null : in;
	}

	private void completeItemsWithFilters(EntityData data) {
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