package mobile.web.webxt.client.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobile.common.message.EntityData;
import mobile.common.message.Field;
import mobile.common.message.Item;
import mobile.common.message.Message;

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

		if(entityData != null){
			for (Item item : entityData.getItemList()) {
				ModelData model = new BaseModelData();
				for (Field field : item.getFieldList()) {
					model.set(field.getName(), field.getValue());
				}
				models.add(model);
			}
		}

		return models;
	}
	
	public static Map<String, String> toMap(Message msg) {
		Map<String,String> mrfields = new HashMap<String, String>(); 

		for (EntityData data : msg.getEntityDataList()) {
			for (Item item : data.getItemList()) {
				for (Field field : item.getFieldList()) {
					mrfields.put(data.getDataId() + FS 
							+ field.getName() + FS
							+ item.getNumber(), field.getValue());
				}
			}
		}
		
		return mrfields;
	}
}