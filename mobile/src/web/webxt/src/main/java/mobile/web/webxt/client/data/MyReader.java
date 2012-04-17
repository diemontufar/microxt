package mobile.web.webxt.client.data;

import java.util.ArrayList;
import java.util.List;

import mobile.common.message.EntityData;
import mobile.common.message.Field;
import mobile.common.message.Item;
import mobile.common.message.Message;
import mobile.web.webxt.client.data.form.DataSource;
import mobile.web.webxt.client.data.form.DataSourceType;
import mobile.web.webxt.client.parser.Parser;
import mobile.web.webxt.client.util.WebConverter;
import mobile.web.webxt.client.windows.MobileError;

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
		System.out.println(">>MyReader.read...");
		ArrayList<ModelData> models = new ArrayList<ModelData>();
		PagingLoadResult<ModelData> paginatedModels = new BasePagingLoadResult<ModelData>(models);

		// Configuration
		MyProcessConfig config = (MyProcessConfig) loadConfig;

		try {
			String strData = data.toString();
			Parser parser = new Parser();
			Message msg = parser.parseMsg(strData, MSG_TYPE);
			System.out.println(">>Entity: " + config.getReference().getEntity());
			EntityData entityData = msg.getEntityData(config.getReference().getEntity());

			System.out.println("Reading items");
			for (Item item : entityData.getItemList()) {
				ModelData model = new BaseModelData();
				for (DataSource ds : config.getlDataSource()) {
					// System.out.println("::DS: " + ds.getAlias() + "-" +
					// ds.getField() + "-" + ds.getType());
					Field field = null;
					if (ds.getType() == DataSourceType.RECORD) {
						field = item.getField(ds.getField());
					} else if (ds.getType() == DataSourceType.DESCRIPTION) {
						field = item.getField(ds.getAlias() + "_" + ds.getField());
					}
					// if (field != null && field.getValue() != null) {
					if (field != null) {
						String modelPropertyName = ds.getField();
						if (ds.getType() == DataSourceType.DESCRIPTION) {
							modelPropertyName = ds.getAlias() + "_" + ds.getField();
						}
						model.set(modelPropertyName, WebConverter.convertToType(field.getValue()));

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
			if (entityData.getTotal() != null) {
				paginatedModels.setTotalLength(entityData.getTotal());
			}

		} catch (Exception e) {
			e.printStackTrace();
			MobileError.report("MENSAJE DE RETORNO INCORRECTO");
		}

		return (PagingLoadResult<ModelData>) paginatedModels;
	}

	public Message readMessage(Object loadConfig, Object data) throws Exception{
		System.out.println("MyReader.readMessage" + data.toString());

		Message msg = null;

		String strData = data.toString();

		System.out.println("Parsing");
		Parser parser = new Parser();
		msg = parser.parseMsg(strData, MSG_TYPE);

		return msg;
	}

	@SuppressWarnings("unchecked")
	protected Object createReturnData(Object loadConfig, List<ModelData> records, int totalCount) {
		return (PagingLoadResult<ModelData>) records;
	}
}