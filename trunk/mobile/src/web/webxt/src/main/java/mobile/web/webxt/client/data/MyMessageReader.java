package mobile.web.webxt.client.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobile.message.message.EntityData;
import mobile.message.message.Field;
import mobile.message.message.Item;
import mobile.message.message.Message;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;

/**
 * Tool for converting/extracting information from Message into
 * useful forms
 */
public final class MyMessageReader {
	
	/** Field separator */
	public final static String FS = ":";  

	public static List<ModelData> getModels(Message msg, String entityName) {
		ArrayList<ModelData> models = new ArrayList<ModelData>();

		EntityData entityData = msg.getEntityData(entityName);

		for (Item item : entityData.getItemList()) {
			ModelData model = new BaseModelData();
			for (Field field : item.getFieldList()) {
				model.set(field.getName(), field.getValue());
			}
			models.add(model);
		}

		return models;
	}
	
	public static Map<String, String> toMap(Message msg) {
		Map<String,String> mrfields = new HashMap<String, String>(); 

		for (EntityData data : msg.getEntityDataList()) {
			for (Item item : data.getItemList()) {
				for (Field field : item.getFieldList()) {
					mrfields.put(data.getId() + FS 
							+ field.getName() + FS
							+ item.getNumber(), field.getValue());
				}
			}
		}
		
		return mrfields;
	}
}