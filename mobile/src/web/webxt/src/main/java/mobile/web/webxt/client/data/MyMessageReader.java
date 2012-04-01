package mobile.web.webxt.client.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mobile.common.message.Data;
import mobile.common.message.EntityData;
import mobile.common.message.Field;
import mobile.common.message.Item;
import mobile.common.message.Message;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.data.form.Dependency;
import mobile.web.webxt.client.util.WebConverter;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;

/**
 * Tool for converting/extracting information from Message into useful forms
 */
public final class MyMessageReader {

	/** Field separator */
	public final static String FS = ":";

	public static List<ModelData> getModels(Message msg, String entityName) throws Exception {
		ArrayList<ModelData> models = new ArrayList<ModelData>();

		EntityData entityData = msg.getEntityData(entityName);

		if (entityData != null) {
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

	public static String buildKey(DataSource ds) {
		// Set map of fields and values
		// Key format: <ALIAS>:<FIELD>:<TYPE>:<PROPERTIES(optional)>
		// Example:
		// sol1:pk_solicitudeId:RECORD
		//
		// ProductMicrocredit:description :DESCRIPTION :
		// Province:name:DESCRIPTION:pk_countryId&per&countryId^pk_provinceId&per&provinceId
		// Country:name:DESCRIPTION:pk_countryId=per=country_Id
		//
		// sol1:pk_solicitudeId:CRITERION :=
		//
		// sol1:solicitudeData:ORDER :DESC
		//
		// <empty>:generatedId:CONTROL

		// Set key
		String key = ds.getAlias() + ":" + ds.getField() + ":" + ds.getType();

		if (ds.getType() == DataSourceType.CRITERION || ds.getType() == DataSourceType.ORDER) {
			key = key + ":" + ds.getComparator();
		} else if (ds.getType() == DataSourceType.DESCRIPTION && ds.getDependencies() != null && ds.getDependencies().size()>0) {
			String params = "";
			for (Dependency d : ds.getDependencies()) {
				if(params.length()!=0){
					params = params + "^";
				}
				String param = d.getField() + "=" + d.getFromAlias() + "=" + d.getFromField();
				params = params + param; 
			}
			key = key + ":" + params;
		}

		return key;
	}

	public static Map<String, Object> toMap(Message msg) {
		Map<String, Object> mrfields = new HashMap<String, Object>();

		boolean hasAtLeastOneItem = false;

		for (EntityData data : msg.getEntityDataList()) {
			// Criterion
			String filters = data.getFilters();
			if (filters != null) {
				String[] filtersArray = filters.split(";");
				for (String filter : filtersArray) {
					String[] part = filter.split(":");
					String field = part[0];
					String comparator = part[1];
					String value = null;
					if (part.length > 2)
						value = part[2];

					Object cValue = WebConverter.convertToType(value);
					mrfields.put(data.getAlias() + FS + field + FS + DataSourceType.CRITERION + FS + comparator, cValue);
				}
			}

			// Record
			for (Item item : data.getItemList()) {
				hasAtLeastOneItem = true;
				for (Field field : item.getFieldList()) {
					// RECORD | DESCRIPTION
					Object cValue = WebConverter.convertToType(field.getValue());
					if (field.getName().indexOf("_") < 0 && !field.getName().substring(0,1).matches("[A-Z]")) {
						mrfields.put(data.getAlias() + FS + field.getName() + FS + DataSourceType.RECORD, cValue);
					} else {
						String[] part = field.getName().split("_");
						String entity = part[0];
						String dfield = part[1];
						mrfields.put(entity + FS + dfield + FS + DataSourceType.DESCRIPTION, cValue);
					}
				}
			}
		}

		// Control fields
		Data controlData = msg.getData(Message.CONTROL_DATA);
		if (controlData != null) {
			if (controlData.getFieldList() != null) {
				for (Field field : controlData.getFieldList()) {
					Object cValue = WebConverter.convertToType(field.getValue());
					mrfields.put("" + FS + field.getName() + FS + DataSourceType.CONTROL, cValue);
				}
			}
		}

		// At least one Item
		mrfields.put("ONE_ITEM", hasAtLeastOneItem);

		return mrfields;
	}
}