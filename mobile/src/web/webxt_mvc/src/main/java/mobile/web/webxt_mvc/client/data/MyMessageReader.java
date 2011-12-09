package mobile.web.webxt_mvc.client.data;

import java.util.ArrayList;
import java.util.List;

import mobile.message.wmessage.Data;
import mobile.message.wmessage.Field;
import mobile.message.wmessage.Item;
import mobile.message.wmessage.Message;

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