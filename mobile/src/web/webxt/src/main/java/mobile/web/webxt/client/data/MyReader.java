package mobile.web.webxt.client.data;

import java.util.ArrayList;
import java.util.List;

import mobile.common.message.EntityData;
import mobile.common.message.Field;
import mobile.common.message.Item;
import mobile.common.message.Message;
import mobile.web.webxt.client.parser.Parser;
import mobile.web.webxt.client.util.ConvertionManager;
import mobile.web.webxt.client.windows.AlertDialog;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.DataReader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;

public class MyReader implements DataReader<PagingLoadResult<ModelData>> {

	private final String MSG_TYPE = Message.JSON;

	public MyReader() {
	}

	public PagingLoadResult<ModelData> read(Object loadConfig, Object data) {
		System.out.println("MyReader.read: " + data.toString());
		ArrayList<ModelData> models = new ArrayList<ModelData>();
		PagingLoadResult<ModelData> paginatedModels = new BasePagingLoadResult<ModelData>(
				models);

		// Configuration
		MyProcessConfig config = (MyProcessConfig) loadConfig;

		try {
			String strData = data.toString();
			Parser parser = new Parser();
			Message msg = parser.parseMsg(strData, MSG_TYPE);
			System.out.println("entity: " + config.getEntity());
			EntityData entityData = msg.getEntityData(config.getEntity());

			System.out.println("reading items");
			for (Item item : entityData.getItemList()) {
				ModelData model = new BaseModelData();
				for (String strField : config.getlFields()) {
					Field field = null;
					if (strField.startsWith("d:")) {
						String[] params = strField.split(":");
						field = item.getField(params[1]);
					} else {
						field = item.getField(strField);
					}
					if (field.getValue().startsWith("((Boolean))")) {
						model.set(field.getName(), ConvertionManager.parseBoolean(field
								.getValue().toString().substring(11)));
					} else {
						model.set(field.getName(), field.getValue());
					}
				}
				models.add(model);
			}
//			System.out.println("Modelos");
//			for (ModelData model : models) {
//				for (String prop : model.getPropertyNames()) {
//					System.out.println(prop + ":" + model.get(prop));
//				}
//			}

			// Pagination
			System.out.println("reading pagination");
			if (entityData.getOffset() != null) {
				paginatedModels.setOffset(entityData.getOffset());
			}
			if (entityData.getTotal()!= null) {
				paginatedModels.setTotalLength(entityData.getTotal());
			}

		} catch (Exception e) {
			new AlertDialog("MyReader", e.getMessage()).show();
			e.printStackTrace();
		}

		return (PagingLoadResult<ModelData>) paginatedModels;
	}

	public Message readMessage(Object loadConfig, Object data) {
		System.out.println("MyReader.readMessage" + data.toString());

		Message msg = null;

		if (!data.toString().startsWith("No message received")) {
			try {
				String strData = data.toString();
				
				System.out.println("Parsing");
				Parser parser = new Parser();
				msg = parser.parseMsg(strData, MSG_TYPE);
				System.out.println("End parsing");
			} catch (Exception e) {
				new AlertDialog("MyReader", e.getMessage()).show();
			}
		}

		return msg;
	}

	@SuppressWarnings("unchecked")
	protected Object createReturnData(Object loadConfig,
			List<ModelData> records, int totalCount) {
		return (PagingLoadResult<ModelData>) records;
	}
}