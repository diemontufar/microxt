package mobile.core.processor;

import java.util.ArrayList;
import java.util.List;

import mobile.common.message.EntityData;
import mobile.common.message.Field;
import mobile.common.message.Item;
import mobile.common.message.Message;
import mobile.common.tools.ProcessorTypes;
import mobile.entity.manager.JPManager;
import mobile.entity.schema.GeneralEntity;
import mobile.entity.schema.SequentialKey;
import mobile.tools.common.Log;
import mobile.tools.common.structure.GeneralProcessor;

import org.apache.log4j.Logger;

public class MaintenanceProcessor implements GeneralProcessor {

	Logger log = Log.getInstance();

	@Override
	public Message process(Message msg) throws Exception {
		// Complete items with filters
		for (EntityData data : msg.getEntityDataList()) {
			if (data.getProcessType() != null
					&& data.getProcessType().compareTo(ProcessorTypes.MNT.getShortName()) == 0
					&& data.getFilters() != null) {
				completeItemsWithFilters(data);
			}
		}

		// Process maintenance
		for (EntityData data : msg.getEntityDataList()) {
			if (data.getProcessType() != null
					&& data.getProcessType().compareTo(ProcessorTypes.MNT.getShortName()) == 0) {
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
		setGeneratedSequence(entity, item);
	}

	private void updateEntity(String entityId, Item item) throws Exception {
		GeneralEntity entity = JPManager.parseEntity(entityId, item, false);
		log.info("Update " + entity.toString());
		JPManager.update(entity);
	}

	private void completeItemsWithFilters(EntityData data) {
		// Get completed fields
		List<Field> lfields = new ArrayList<Field>();
		String strFilters = data.getFilters();
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

	private void setGeneratedSequence(GeneralEntity entity, Item item) {
		if (entity.getPk() instanceof SequentialKey) {
			SequentialKey key = (SequentialKey) entity.getPk();
			item.addField(Item.GENERATED_ID, key.getId().toString());
		}
	}

}