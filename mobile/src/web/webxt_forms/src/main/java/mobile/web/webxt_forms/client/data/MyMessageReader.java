package mobile.web.webxt_forms.client.data;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;

import mobile.web.message.Data;
import mobile.web.message.Field;
import mobile.web.message.Item;
import mobile.web.message.Message;

public class MyMessageReader {

	private Message msg;

	public MyMessageReader(Message msg) {
		this.msg = msg;
	}

	public List<ModelData> getModels(String entityName) {
		ArrayList<ModelData> models = new ArrayList<ModelData>();

		Data entityData = msg.getData(entityName);

		for (Item item : entityData.getItemList()) {
			ModelData model = new BaseModelData();
			for (Field field : item.getFieldList()) {
				model.set(field.getName(), field.getValue());
			}
			models.add(model);
		}

		return models;
	}
}