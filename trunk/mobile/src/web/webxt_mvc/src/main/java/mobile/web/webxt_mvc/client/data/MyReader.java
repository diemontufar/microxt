package mobile.web.webxt_mvc.client.data;

import java.util.ArrayList;
import java.util.List;

import mobile.message.wmessage.Data;
import mobile.message.wmessage.Field;
import mobile.message.wmessage.Item;
import mobile.message.wmessage.Message;
import mobile.message.wmessage.Parser;
import mobile.web.webxt_mvc.client.windows.AlertDialog;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.DataReader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;

public class MyReader implements DataReader<PagingLoadResult<ModelData>> {

	private final String MSG_TYPE = Parser.JSON;
	
	private Parser parser;
	
	private final String OFFSET = "_pag_offset";
	
	private final String TOTAL_LENGTH = "_pag_total_length";
	
	public MyReader() {
	}

	public PagingLoadResult<ModelData> read(Object loadConfig, Object data) {
		System.out.println("MyReader.read: " + data.toString());
		ArrayList<ModelData> models = new ArrayList<ModelData>();
		PagingLoadResult<ModelData> paginatedModels = new BasePagingLoadResult<ModelData>(models);
		
		// Configuration
		MyProcessConfig config = (MyProcessConfig) loadConfig;
		
		try {
			String strData = data.toString();
			parser = new Parser(strData, MSG_TYPE);
			Message msg = new Message(parser);
			System.out.println("entity: " + config.getEntity());
			Data entityData = msg.getData(config.getEntity());

			System.out.println("reading items");
			for (Item item : entityData.getItemList()) {
				ModelData model = new BaseModelData();
				for (String strField : config.getlFields()) {
					Field field = null;
					if(strField.startsWith("d:")){
						String[] params = strField.split(":");
						field = item.getField(params[1]);
					}else{
						field = item.getField(strField);
					}
					if (field.getValue().startsWith("((Boolean))")) {
						model.set(field.getName(), new Boolean(field.getValue()));
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
			if (entityData.getField(OFFSET) != null) {
				paginatedModels.setOffset(Integer.valueOf(entityData.getField(
						OFFSET).getValue()));
			}
			if (entityData.getField(TOTAL_LENGTH) != null) {
				paginatedModels.setTotalLength(Integer.valueOf(entityData
						.getField(TOTAL_LENGTH).getValue()));
			}

		} catch (Exception e) {
			new AlertDialog("MyReader", e.getMessage()).show();
			e.printStackTrace();
		}
		
		return (PagingLoadResult<ModelData>) paginatedModels;
	}

	public Message readMessage(Object loadConfig, Object data){
		System.out.println("MyReader.readMessage" + data.toString());
		
		Message msg = null;
		
		if(!data.toString().startsWith("No message received")){
			try {
				String strData = data.toString();
				System.out.println("Parsing");
				
				parser = new Parser(strData, MSG_TYPE);
				msg = new Message(parser);
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