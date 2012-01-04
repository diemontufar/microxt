package mobile.web.webxt.client.data;

import java.util.ArrayList;
import java.util.List;

import mobile.message.message.Data;
import mobile.message.message.Field;
import mobile.message.message.Item;
import mobile.message.message.Message;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelData;

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